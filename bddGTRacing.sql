create table Cargos(
    idCargo serial,
    nombreCargo varchar(45) not null,
    salrioCargo decimal(15, 2) not null,
    descripCargo varchar(250),
    fechaAdd timestamp
    default CURRENT_TIMESTAMP,
    constraint pk_cargos primary key(idCargo)
);

create table Empleados(
    idEmpleado serial not null primary key,
    nombreEmpleado varchar(45) not null,
    apellidoEmpleado varchar(45) not null,
    foto bytea null,
    fechaNac date not null,
    domicilioEmp varchar(250) not null,
    dui varchar(10) not null,
    nit varchar(17) not null,
    idCargo int not null,
    fechaAdd timestamp
    default current_timestamp,
    constraint fk_cargo_empleado foreign key(idCargo) references Cargos(idCargo)
);

create table Roles(
    idRol serial not null,
    nameRol varchar(45) not null,
    descripcion varchar(250),
    fehcaAdd timestamp
    default current_timestamp,
    constraint pk_roles primary key(idRol)
);

create table Tareas(
    idTarea serial not null,
    nameTarea varchar(45) not null,
    descripcion varchar(250),
    fechaAdd timestamp
    default current_timestamp,
    constraint pk_tareas primary key(idTarea)
);

create table Tareas_Roles(
    idActividad serial not null,
    idRol int not null,
    idTarea int not null,
    constraint pk_actividades primary key(idActividad),
    constraint fk_rol_actividad foreign key(idRol) references Roles(idRol),
    constraint fk_tarea_actividad foreign key(idTarea) references Tareas(idTarea)
);

create table Usuarios(
    idUsuario serial not null,
    userName varchar(15) unique not null,
    userPasswd varchar(250) not null,
    idEmpleado int not null,
    idRol int not null,
    fechaAdd timestamp
    default current_timestamp,
    constraint pk_usuarios primary key(idUsuario),
    constraint fk_empleado_usuario foreign key(idEmpleado) references Empleados(idEmpleado),
    constraint fk_rol_usuario foreign key(idRol) references Roles(idRol)
);

create table Repuestos(
    idRepuesto serial not null,
    nameRepuesto varchar(150) not null,
    respuestoIsActive int not null,
    descripcion varchar(250),
    foto bytea null,
    idUserAdd int not null,
    fechaAdd timestamp
    default current_timestamp,
    constraint pk_respuesto primary key(idRepuesto),
    constraint fk_repuesto_user_add foreign key(idUserAdd) references Usuarios(idUsuario)
);

create table Proveedores(
    idProveedor serial not null,
    nameProveedor varchar(100) not null,
    direccion varchar(250) not null,
    pbxProvedor varchar(16),
    fechaAdd timestamp
    default current_timestamp,
    constraint pk_proveedor primary key(idProveedor)
);

create table ExistenciaRepuestos(
    idExistencia serial not null,
    idRepuesto int not null,
    idProveedor int not null,
    idUserAdd int not null,
    cantidad int not null,
    precioCompra decimal(15, 2) not null,
    precioVenta decimal(15, 2) not null,
    fechaAdd timestamp
    default current_timestamp,
    constraint pk_existencia_repuesto primary key(idExistencia),
    constraint fk_existencia_repuesto foreign key(idRepuesto) references Repuestos(idRepuesto),
    constraint fk_proveedor_repuesto foreign key(idProveedor) references Proveedores(idProveedor),
    constraint fk_rep_inv_user_add foreign key(idUserAdd) references Usuarios(idUsuario)
);

create table Marcas(
    idMarca serial not null,
    nameMarca varchar(45) not null,
    paisMarca varchar(100) not null,
    fechaAdd timestamp
    default current_timestamp,
    constraint pk_marcas primary key(idMarca)
);

create table Autos(
    idAuto serial not null,
    idMarca int not null,
    idUserAdd int not null,
    modeloAuto varchar(45) not null,
    anyoAuto int not null,
    foto bytea null,
    constraint pk_autos primary key(idAuto),
    constraint fk_auto_marca foreign key(idMarca) references Marcas(idMarca),
    constraint fk_autos_user_add foreign key(idUserAdd) references Usuarios(idUsuario)
);

create table ExistenciaAutos(
    idExistenciaAutos serial not null,
    idAutos int not null,
    idProveedor int not null,
    idUserAdd int not null,
    precioCompra decimal(15, 2) not null,
    precioVenta decimal(15, 2) not null,
    fechaAdd timestamp
    default current_timestamp,
    constraint pk_existencia_autos primary key(idExistenciaAutos),
    constraint fk_autos_proveedor foreign key(idProveedor) references Proveedores(idProveedor),
    constraint fk_autos_existencias foreign key(idAutos) references Autos(idAuto),
    constraint fk_auto_inv_user_add foreign key(idUserAdd) references Usuarios(idUsuario)
);

create table Factura(
    idFactura serial not null,
    idUsuario int not null,
    cliente varchar(150) not null,
    total decimal(15, 2) not null,
    fechaAdd timestamp
    default current_timestamp,
    constraint pk_factura primary key(idFactura),
    constraint fk_factura_usuario foreign key(idUsuario) references Usuarios(idUsuario)
);

create table DetalleFactura(
    idDetalle serial not null,
    idFactura int not null,
    idAuto int,
    idRepuesto int,
    cantidad int not null,
    precioUnitario decimal(15, 2) not null,
    subTotal decimal(15, 2) not null,
    constraint pk_detalle_factura primary key(idDetalle),
    constraint fk_detalle_auto foreign key(idAuto) references Autos(idAuto),
    constraint fk_detalle_repuesto foreign key(idRepuesto) references Repuestos(idRepuesto),
    constraint fk_factura_detalle foreign key(idFactura) references Factura(idFactura)
);

-- - TABLAS PARA SITEMA WEB-- -
create table usuarioweb(
    idWebUser serial not null,
    pName varchar(40) not null,
    sName varchar(40) null,
    pApellido varchar(40) not null,
    sApellido varchar(40) null,
    userName varchar(15) not null,
    userPwd varchar(250) not null,
    fechaNac date not null,
    fechaAdd timestamp
    default current_timestamp,
    constraint pk_userweb primary key(idWebUser)
);

create table cuentaCompras(
    idCuenta serial not null,
    idWebUser int not null,
    tarjetaBanco varchar(250) not null,
    secureKey varchar(250) not null,
    cantidadDinero decimal(15, 2) not null,
    constraint pk_cuenta_compras primary key(idCuenta),
    constraint fk_web_user foreign key(idWebUser) references usuarioweb(idWebUser)
);

create table reservaProducto(
    idReserva varchar(15) not null,
    totalReserva decimal(15, 2) not null,
    idWebUser int not null,
    fechaAdd timestamp
    default current_timestamp,
    constraint pk_reserva primary key(idReserva),
    constraint fk_webuser_reserva foreign key(idWebUser) references usuarioweb(idWebUser)
);

create table detalleReserva(
    idDetalle serial not null,
    idReserva varchar(15) not null,
    idRepuesto int null,
    idAuto int null,
    cantidad int not null,
    subTotal decimal(15,2) not null,
    constraint pk_detalle_reserva primary key(idDetalle),
    constraint fk_reserva_deatlle foreign key(idReserva) references reservaProducto(idReserva),
    constraint fk_reserva_repuesto foreign key(idRepuesto) references Repuestos(idRepuesto),
    constraint fk_reserva_auto foreign key(idAuto) references Autos(idAuto)
);

-- - INGRESO DE DATOS-- -
insert into cargos(nombrecargo, salriocargo, descripcargo) values('Gerente', '1250.35', 'Persona encargada de las operaciones');
insert into empleados(nombreempleado, apellidoempleado, fechanac, domicilioemp, dui, nit, idcargo) values('Marvin Leonel', 'Rivas Trejo', '1993-10-04', 'Urb Valle de San Jacinto Pol. G Pje. Los Abetos #1213', '08734123-2', '0921-041093-234-1', 1);
insert into roles(namerol, descripcion) values('SuperUser', 'puede ejecutar cualquier tarea dentro del sistema');
insert into roles(namerol, descripcion) values('Gerente', 'hace algo');
insert into usuarios(username, userpasswd, idempleado, idrol) values('leon04', 'oLxhlu0NHA7+SWipWfLEfQ==', 1, 1);
-- - INGRESO DE TAREAS-- -
INSERT INTO tareas(nametarea, descripcion) VALUES('CargosAdd', 'Puede agregar cargos');
INSERT INTO tareas(nametarea, descripcion) VALUES('CargosUpdate', 'Puede editar cargos');
INSERT INTO tareas(nametarea, descripcion) VALUES('CargosDelete', 'Puede eliminar cargos');
INSERT INTO tareas(nametarea, descripcion) VALUES('EmpleadosAdd', 'Puede agregar empleados');
INSERT INTO tareas(nametarea, descripcion) VALUES('EmpleadosUpdate', 'Puede editar empleados');
INSERT INTO tareas(nametarea, descripcion) VALUES('EmpleadosDelete', 'Puede eliminar empleados');
INSERT INTO tareas(nametarea, descripcion) VALUES('RolessAdd', 'Puede agregar roles');
INSERT INTO tareas(nametarea, descripcion) VALUES('RolesUpdate', 'Puede editar roles');
INSERT INTO tareas(nametarea, descripcion) VALUES('RolesDelete', 'Puede eliminar roles');
INSERT INTO tareas(nametarea, descripcion) VALUES('UsuariosAdd', 'Puede agregar usuarios');
INSERT INTO tareas(nametarea, descripcion) VALUES('UsuariosUpdate', 'Puede editar usuarios');
INSERT INTO tareas(nametarea, descripcion) VALUES('UsuariosDelete', 'Puede eliminar usuarios');
INSERT INTO tareas(nametarea, descripcion) VALUES('ProveedoresAdd', 'Puede agregar proveedores');
INSERT INTO tareas(nametarea, descripcion) VALUES('ProveedoresUpdate', 'Puede editar proveedores');
INSERT INTO tareas(nametarea, descripcion) VALUES('ProveedoresDelete', 'Puede eliminar proveedores');
INSERT INTO tareas(nametarea, descripcion) VALUES('RepuestossAdd', 'Puede agregar repuestos');
INSERT INTO tareas(nametarea, descripcion) VALUES('RepuestosUpdate', 'Puede editar repuestos');
INSERT INTO tareas(nametarea, descripcion) VALUES('RepuestosDelete', 'Puede eliminar repuestos');
INSERT INTO tareas(nametarea, descripcion) VALUES('AutosAdd', 'Puede agregar autos');
INSERT INTO tareas(nametarea, descripcion) VALUES('AutosUpdate', 'Puede editar autos');
INSERT INTO tareas(nametarea, descripcion) VALUES('AutosDelete', 'Puede eliminar autos');
INSERT INTO tareas(nametarea, descripcion) VALUES('Ventas', 'Acceso a ventas');
INSERT INTO tareas(nametarea, descripcion) VALUES('Estadisticas', 'Acceso a Estadisticas');
-- - RELACION ROL - TAREAS-- -
INSERT INTO Tareas_Roles(idrol, idtarea) VALUES(1, 1);
INSERT INTO Tareas_Roles(idrol, idtarea) VALUES(1, 2);
INSERT INTO Tareas_Roles(idrol, idtarea) VALUES(1, 3);
INSERT INTO Tareas_Roles(idrol, idtarea) VALUES(1, 4);
INSERT INTO Tareas_Roles(idrol, idtarea) VALUES(1, 5);
INSERT INTO Tareas_Roles(idrol, idtarea) VALUES(1, 6);
INSERT INTO Tareas_Roles(idrol, idtarea) VALUES(1, 7);
INSERT INTO Tareas_Roles(idrol, idtarea) VALUES(1, 8);
INSERT INTO Tareas_Roles(idrol, idtarea) VALUES(1, 9);
INSERT INTO Tareas_Roles(idrol, idtarea) VALUES(1, 10);
INSERT INTO Tareas_Roles(idrol, idtarea) VALUES(1, 11);
INSERT INTO Tareas_Roles(idrol, idtarea) VALUES(1, 12);
INSERT INTO Tareas_Roles(idrol, idtarea) VALUES(1, 13);
INSERT INTO Tareas_Roles(idrol, idtarea) VALUES(1, 14);
INSERT INTO Tareas_Roles(idrol, idtarea) VALUES(1, 15);
INSERT INTO Tareas_Roles(idrol, idtarea) VALUES(1, 16);
INSERT INTO Tareas_Roles(idrol, idtarea) VALUES(1, 17);
INSERT INTO Tareas_Roles(idrol, idtarea) VALUES(1, 18);
INSERT INTO Tareas_Roles(idrol, idtarea) VALUES(1, 19);
INSERT INTO Tareas_Roles(idrol, idtarea) VALUES(1, 20);
INSERT INTO Tareas_Roles(idrol, idtarea) VALUES(1, 21);
INSERT INTO Tareas_Roles(idrol, idtarea) VALUES(1, 22);
INSERT INTO Tareas_Roles(idrol, idtarea) VALUES(1, 23);