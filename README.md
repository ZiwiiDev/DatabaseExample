# DatabaseExample
Aplicación móvil muy simple para Android Nativa con Java que desarrolla un pequeño ejemplo de cómo trabajar con una base de datos.

Crear una aplicación para gestionar los productos que un supermercado tenga en stock, junto con la
información de interés de cada producto. Para simplificar la base de datos, sólo tendremos una tabla
PRODUCTOS, con las siguientes características: nombre, precio de venta, ingredientes, peso y
campo de no disponible o disponible. El usuario realizará la carga de los productos que desee. Si se
ha equivocado podrá borrar los datos y cargarlos de nuevo. El listado de productos deberá mostrar
todos los productos, ajustándose a las preferencias de búsqueda que seleccione el usuario. Debe
existir alguna distinción visual en el listado entre productos no disponibles y disponibles (icono,
formato del texto, color de fondo, letra: D(disponible) o N(no disponible) etc.) Pulsando sobre
alguno de los productos accederemos a una pantalla con el detalle de dicho producto, donde
podremos modificar los datos del producto y marcar el producto como no disponible. Para
desarrollar dicha aplicación deberás realizar cada uno de los siguientes apartados:

La aplicación consiste en una pantalla principal, una pantalla para Listar Productos, una
pantalla para ver la Ficha completa del Producto con la opción de marcarlo como no
disponible, una Pantalla de Preferencias, una pantalla para Insertar Productos y una pantalla
para Eliminar Productos.

1. “Pantalla principal” que debe tener el nombre de la aplicación y una imagen representativa, a
modo de botón para acceder a la pantalla "Listado de Productos". 

![image](https://user-images.githubusercontent.com/100787553/234547132-2dc89a54-acf8-462d-9978-256c354fdde4.png)

Si hacemos click en la imagen nos llevará a la pantalla Listado Producto.

Pantalla "Listado de Productos": Contendrá una lista con todos los productos almacenados en la
base de datos. Deben aparecer aquellos que cumplan con los filtros establecidos en la Pantalla
Preferencias. En el listado aparecerá la siguiente información en cada uno de los elementos:
  o Un pequeño icono que indicará si el producto está disponible o no. Sabremos si ese
  producto está disponible si el campo "disponible" en la base de datos es 1 (verdadero).
  o El nombre del producto
  o El precio de venta
  o Al pulsar sobre un producto, se abrirá la Pantalla "Ficha de producto" para poder modificar
  los datos o marcar el producto como no disponible. 

![image](https://user-images.githubusercontent.com/100787553/234547284-73359b8f-d757-4855-8c2f-a9554e658257.png)

En el menú tenemos tenemos 3 opciones. Una para insertar un producto que nos llevará a la pantalla
Insertar Producto, otro para eliminar un producto que nos llevará a la pantalla Eliminar Producto, y
otro para el filtrado de preferencias que nos llevará a la Pantalla Preferencias.

Un menú con las siguientes opciones:
  o Insertar Producto: Al pulsar en este icono se mandará al usuario a la pantalla "Insertar
  Producto", donde podrá insertar un nuevo producto en la tabla PRODUCTOS con toda la información requerida.
  o Preferencias : Al pulsar en este icono se mandará al usuario a la pantalla preferencias 

![image](https://user-images.githubusercontent.com/100787553/234547371-42424908-73b3-494b-a5e9-a3084a6ee6f3.png)

Pantalla "Preferencias": Esta pantalla se utilizará como filtro para mostrar o todos los productos o
solamente los productos disponibles. Si el SwitchPreference está activado muestra sólo los
productos disponibles, y si lo desactivamos muestra todos los productos.

![image](https://user-images.githubusercontent.com/100787553/234547420-b6e63b51-957e-42fd-9aa3-df94fda40e43.png)

![image](https://user-images.githubusercontent.com/100787553/234547453-5bf719b8-7c91-4084-9409-7760807e296e.png)

Pantalla "Ficha de Producto": Una vez se selecciona un elemento de la lista en la Pantalla
Listado de productos, se abrirá otra pantalla donde se mostrará el detalle del producto, pudiendo
cambiar cada uno de los siguientes valores:
  o Nombre del producto.
  o Precio de venta.
  o Ingredientes que contiene.
  o Peso neto del producto en gramos.
  o Disponible, será un elemento "checkbox" para marcar si el producto está disponible o no.

Por último, pondremos un botón para guardar y otro para regresar a la Pantalla Listado de Productos.

![image](https://user-images.githubusercontent.com/100787553/234547544-03ebd1dc-8f8e-499d-b229-92cb54f18318.png)

En esta pantalla podemos editar el producto seleccionado del adaptador y cambiarlo a disponible o
no disponible.

Pantalla "Insertar Producto": En esta pantalla se mostrará una ficha para introducir un nuevo
producto. Contendrá los siguientes campos:
   o Nombre del producto
  o Precio de venta o Ingredientes que contiene
  o Peso del producto en gramos
  o El campo disponible (checkbox) Por último, pondremos un botón para guardar y otro para
  regresar a la Pantalla Listado de Productos. 

![image](https://user-images.githubusercontent.com/100787553/234547609-8c696b72-95c1-4aa1-ab13-c050afc12adc.png)

Podemos agregar un nuevo producto y marcar si está disponible o no.

Pantalla "Eliminar Producto": Contendrá una lista con todos los productos
almacenados en la base de datos. En el listado aparecerá la siguiente información de cada uno de los
elementos: o Nombre del producto o Precio de venta o Botón para borrar el producto de la base de
datos. La pantalla Eliminar Producto, será accesible al pulsar en una opción del menú principal. 

![image](https://user-images.githubusercontent.com/100787553/234547723-f8050ecb-dfd7-4119-bd37-9224bf9a0409.png)

Este activity permite eliminar un producto de la base de datos.

Mensajes:
En la pantalla principal, si el usuario accede por primera vez y pulsa sobre el botón para acceder al
Listado de Productos, no aparecerá ninguna información debido a que todavía no se han cargado los
datos en la tabla correspondiente. Se debe informar al usuario con un mensaje de duración corta que
primero debe cargar los datos. Es necesario, por tanto, que primero se acceda al menú para que
cargue algún producto en la base de datos.

![image](https://user-images.githubusercontent.com/100787553/234547785-78e497a7-5399-40ad-9a7f-02c2f1005e5c.png)

A la hora de insertar productos en la base de datos, además de realizar la operación correspondiente
se debe informar al usuario de la aplicación con un mensaje de duración corta que diga que "Se ha
insertado correctamente el producto". 

![image](https://user-images.githubusercontent.com/100787553/234547837-c5a2a78d-a521-4104-b7bd-e1f241c1eb92.png)

Cuando se cambie uno de los parámetros del producto en la pantalla "Ficha de producto", se debe
informar al usuario de la aplicación con un mensaje de duración corta que diga que "Se ha
actualizado el producto".

![image](https://user-images.githubusercontent.com/100787553/234547907-d3cebb7d-5788-47ab-a67b-a93e7aaf1cb8.png)

Implementar la gestión de los datos
Utiliza una base de datos que contenga una única tabla llamada PRODUCTO.
Debes crearla con los siguientes campos:
  • id: del producto. Debe de ser único y auto incrementable.
  • nombre: del producto.
  • ingredientes: del producto.
  • gr: del producto en gramos.
  • precio: de venta del producto.
  • disponible: valor lógico que indica si el producto está disponible (1) o no (0)

![image](https://user-images.githubusercontent.com/100787553/234548001-9c25dd18-b5fa-4867-a240-d290d88f0b34.png)

Adaptador para mostrar el listado de productos.

![image](https://user-images.githubusercontent.com/100787553/234548054-35e50dcf-5f7c-40ce-baec-9133d8f95780.png)

Adaptador con botón para eliminar un producto.

![image](https://user-images.githubusercontent.com/100787553/234548081-7bb3698a-1750-4ae1-9e4d-8a33da6f7ef4.png)

Creo un menu.xml y una toolbar.xml

![image](https://user-images.githubusercontent.com/100787553/234548121-2552d665-c729-4167-b951-f563483ecfe7.png)

En res/xml creamos settings.xml para las preferencias.

![image](https://user-images.githubusercontent.com/100787553/234548158-f35e0761-e003-41cb-8029-414a17e77e74.png)

Una clase Producto con atributos, constructores, getters y setters de los productos

![image](https://user-images.githubusercontent.com/100787553/234548187-bad14d8d-5e2f-4ca5-8577-36e02b2d2752.png)

Clase para las preferencias.

![image](https://user-images.githubusercontent.com/100787553/234548222-04ea3fe5-0d64-4d21-b0e2-3e5a162c5e5b.png)

¡¡ GRACIAS, UN SALUDO !!
