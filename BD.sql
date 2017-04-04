/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  sanch
 * Created: Apr 4, 2017
 */

create table Cargos(
idCargo serial ,
nombreCargo varchar(45) not null,
salrioCargo decimal(15,2) not null,
descripCargo varchar(250),
fechaAdd timestamp default CURRENT_TIMESTAMP,
constraint pk_cargos primary key (idCargo)
);

create table Empleados(
idEmpleado serial not null primary key,
nombreEmpleado varchar(45) not null,
apellidoEmpleado varchar(45) not null,
fechaNac date not null,
domicilioEmp varchar(250) not null,
dui varchar(10) not null,
nit varchar(17) not null,
idCargo int not null,
fechaAdd timestamp default current_timestamp,
constraint fk_cargo_empleado foreign key (idCargo) references Cargos(idCargo)
);

create table Roles(
idRol serial not null,
nameRol varchar(45) not null,
descripcion varchar(250),
fehcaAdd timestamp default current_timestamp,
constraint pk_roles primary key (idRol)
);

create table Tareas(
idTarea serial not null,
nameTarea varchar(45) not null,
descripcion varchar(250),
fechaAdd timestamp default current_timestamp,
constraint pk_tareas primary key (idTarea)
);

create table Tareas_Roles(
idActividad serial not null,
idRol int not null,
idTarea int not null,
constraint pk_actividades primary key (idActividad),
constraint fk_rol_actividad foreign key (idRol) references Roles(idRol),
constraint fk_tarea_actividad foreign key (idTarea) references Tareas(idTarea)
);

create table Usuarios(
idUsuario serial not null,
userName varchar(15) not null,
userPasswd varchar(250) not null,
idEmpleado int not null,
idRol int not null,
fechaAdd timestamp default current_timestamp,
constraint pk_usuarios primary key (idUsuario),
constraint fk_empleado_usuario foreign key (idEmpleado) references Empleados(idEmpleado),
constraint fk_rol_usuario foreign key (idRol) references Roles(idRol)
);

create table Repuestos(
idRepuesto serial not null,
nameRepuesto varchar(150) not null,
repuestoIsActive boolean not null,
descripcion varchar(250),
idUserAdd int not null,
fechaAdd timestamp default current_timestamp,
constraint pk_respuesto primary key (idRepuesto),
constraint fk_repuesto_user_add foreign key (idUserAdd) references Usuarios (idUsuario)
);

create table Proveedores(
idProveedor serial not null,
nameProveedor varchar(100) not null,
direccion varchar(250) not null,
pbxProvedor varchar(16),
fechaAdd timestamp default current_timestamp,
constraint pk_proveedor primary key (idProveedor)
);

create table ExistenciaRepuestos(
idExistencia serial not null,
idRepuesto int not null,
idProveedor int not null,
idUserAdd int not null,
cantidad int not null,
precioCompra decimal(15,2) not null,
precioVenta decimal(15,2) not null,
fechaAdd timestamp default current_timestamp,
constraint pk_existencia_repuesto primary key (idExistencia),
constraint fk_existencia_repuesto foreign key (idRepuesto) references Repuestos(idRepuesto),
constraint fk_proveedor_repuesto foreign key (idProveedor) references Proveedores(idProveedor),
constraint fk_rep_inv_user_add foreign key (idUserAdd) references Usuarios(idUsuario)
);

create table Marcas(
idMarca serial not null,
nameMarca varchar(45) not null,
paisMarca varchar(100) not null,
fechaAdd timestamp default current_timestamp,
constraint pk_marcas primary key (idMarca)
);

create table Autos(
idAuto serial not null,
idMarca int not null,
idUserAdd int not null,
modeloAuto varchar(45) not null,
anyoAuto int not null,
constraint pk_autos primary key (idAuto),
constraint fk_auto_marca foreign key (idMarca) references Marcas(idMarca),
constraint fk_autos_user_add foreign key (idUserAdd) references Usuarios(idUsuario)
);

create table ExistenciaAutos(
idExistenciaAutos serial not null,
idAutos int not null,
idProveedor int not null,
idUserAdd int not null,
precioCompra decimal(15,2) not null,
precioVenta decimal(15,2) not null,
fechaAdd timestamp default current_timestamp,
constraint pk_existencia_autos primary key (idExistenciaAutos),
constraint fk_autos_proveedor foreign key (idProveedor) references Proveedores (idProveedor),
constraint fk_autos_existencias foreign key (idAutos) references Autos (idAuto),
constraint fk_auto_inv_user_add	foreign key (idUserAdd) references Usuarios(idUsuario)
);

create table Factura(
idFactura serial not null,
idUsuario int not null,
cliente varchar(150) not null,
total decimal(15,2) not null,
fechaAdd timestamp default current_timestamp,
constraint pk_factura primary key (idFactura),
constraint fk_factura_usuario foreign key (idUsuario) references Usuarios (idUsuario)
);

create table DetalleFactura(
idDetalle serial not null,
idFactura int not null,
idAuto int,
idRepuesto int,
cantidad int not null,
precioUnitario decimal(15,2) not null,
subTotal decimal(15,2) not null,
constraint pk_detalle_factura primary key (idDetalle),
constraint fk_detalle_auto foreign key (idAuto) references Autos (idAuto),
constraint fk_detalle_repuesto foreign key (idRepuesto) references Repuestos (idRepuesto),
constraint fk_factura_detalle foreign key (idFactura) references Factura (idFactura)
);

----------------------------------------------------------------------------------------------------------------------------------------------------
								--- INGRESO DE DATOS ---
----------------------------------------------------------------------------------------------------------------------------------------------------

insert into cargos(nombrecargo, salriocargo, descripcargo) values('Gerente','1250.35','Persona encargada de las operaciones');
insert into empleados(nombreempleado, apellidoempleado, fechanac, domicilioemp, dui, nit, idcargo) values('Marvin Leonel','Rivas Trejo','1993-10-04','Urb Valle de San Jacinto Pol. G Pje. Los Abetos #1213','08734123-2','0921-041093-234-1',1);
insert into roles(namerol,descripcion) values('SuperUser','puede ejecutar cualquier tarea dentro del sistema');
insert into usuarios(username,userpasswd,idempleado,idrol) values('leon04','oLxhlu0NHA7+SWipWfLEfQ==',1,1);