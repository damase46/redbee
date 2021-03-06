# redbee
Supuestos:
Suponemos que a la hora de persistir, una vez completada la tarea se notificara el estado (si hubo error o no) a un socket donde se tratara en su debida forma.

El codigo refleja el siguiente problema:

Problema:
Actualmente con solo 2 entidades Hotel, Comentario. A la hora de realizar un update a un mensaje, o agregar un mensaje a un hotel deberia primero traerme todo el hotel con todos sus comentarios. Es decir, a la hora de agregar un comentario a un hotel tengo que traerme todos los comentarios que tiene para sumarle uno.
Lo mismo sucede con comentario al agregar una respuesta.

UPDATE
Solucion: se creo un nuevo servicio updateCustom (server/api/hotel/updateCustom) donde se realiza el update/insert manualmente de las entidades con las herramientas que brinda MongoDB. (clases: HotelRepositoryImpl, ComentarioRepositoryImpl)

Nota: inicializar secuencias: hotelSequense, commentSequense
{
    "_id" : "hotelSequense",
    "seq" : 0
},
{
    "_id" : "commentSequense",
    "seq" : 0
}

Ejemplo de entrada:

{
  "name": "Hotel Stefanos",
  "stars": 3,
  "price": 994.18,
  "comments": [
    {
      "name": "sergio",
      "comment": "muy bueno"
    }
  ]
}

Resultado db

Hotel: 
{
    "_id" : NumberLong(1),
    "comments" : [ 
        [ 
            {
                "$ref" : "comment",
                "$id" : NumberLong(1)
            }
        ]
    ],
    "name" : "Hotel Stefanos",
    "price" : 994.18,
    "stars" : 3
}

Comentario:
{
    "_id" : NumberLong(1),
    "comment" : "muy bueno",
    "name" : "sergio"
}

OLD
1. Solucion 1: Para el caso de que la entidad comentario no se pueda modificar
  - Crear una entidad intermedia en cada relacion (Hotel - comentario / comentario - comentario) de la siguiente forma:

  Hotel - comentario:
  {
    idHotel,
    idComentario,
    (en el caso que tengamos id de usuario tambie )
  }

  Comentario - Comentario:
  {
    idComment,
    idReply,
  }

Lo que permitira que a la hora de realizar una persistencia se toque una tabla sin hacer una consulta previa o tener el objeto completo.
La desventaja es que por cada insert o delete deberiamos tener un trigger para hacer los updates correspondientes

2. Solucion 2: en el caso que podamos modificar el modelo
  -LLevar el modelo a segunda forma normal, donde comentario tendria id de hotel e id de comentario padre como a continuación

  {
    "id": 24994,
    "name": "Carlitos",
    "comment": "estaba buenisimo",
    "date": 12/03/2018,
    "idCommentPadre": 12123,
    "idHotel": 12323,
  }
  
  En este caso hariamos un solo update pero estariamos atando a la entidad de comentario a hotel.
