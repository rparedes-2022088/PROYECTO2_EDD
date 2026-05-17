CREATE TABLE rol (
    id_rol NUMBER PRIMARY KEY,
    nombre_rol VARCHAR2(30) NOT NULL UNIQUE
);

CREATE SEQUENCE seq_roles
START WITH 1
INCREMENT BY 1;

CREATE OR REPLACE TRIGGER trg_roles
BEFORE INSERT ON rol
FOR EACH ROW
BEGIN
    :NEW.id_rol := seq_roles.NEXTVAL;
END;
/

CREATE TABLE usuarios (
    id_usuario NUMBER PRIMARY KEY,
    nombre VARCHAR2(100) NOT NULL,
    apellido VARCHAR2(100) NOT NULL,
    correo VARCHAR2(150) UNIQUE NOT NULL,
    pass VARCHAR2(100) NOT NULL,
    telefono VARCHAR2(20),
    direccion VARCHAR2(200),
    fecha_registro DATE DEFAULT SYSDATE,
    id_rol NUMBER NOT NULL,

    CONSTRAINT fk_usuario_rol
        FOREIGN KEY (id_rol)
        REFERENCES rol(id_rol)
);

CREATE SEQUENCE seq_usuarios
START WITH 1
INCREMENT BY 1;

CREATE OR REPLACE TRIGGER trg_usuarios
BEFORE INSERT ON usuarios
FOR EACH ROW
BEGIN
    :NEW.id_usuario := seq_usuarios.NEXTVAL;
END;
/


CREATE TABLE pedidos (
    id_pedido NUMBER PRIMARY KEY,
    descripcion VARCHAR2(250) NOT NULL,
    direccion_entrega VARCHAR2(250) NOT NULL,
    fecha_pedido DATE DEFAULT SYSDATE,
    estado VARCHAR2(30) DEFAULT 'PENDIENTE',
    prioridad VARCHAR2(20) DEFAULT 'NORMAL',

    id_cliente NUMBER NOT NULL,
    id_repartidor NUMBER,

    CONSTRAINT fk_cliente
        FOREIGN KEY (id_cliente)
        REFERENCES usuarios(id_usuario),

    CONSTRAINT fk_repartidor
        FOREIGN KEY (id_repartidor)
        REFERENCES usuarios(id_usuario)
);

CREATE SEQUENCE seq_pedidos
START WITH 1
INCREMENT BY 1;

CREATE OR REPLACE TRIGGER trg_pedidos
BEFORE INSERT ON pedidos
FOR EACH ROW
BEGIN
    :NEW.id_pedido := seq_pedidos.NEXTVAL;
END;
/


CREATE TABLE arbol_binario (
    id_nodo NUMBER PRIMARY KEY,

    clave_arbol NUMBER NOT NULL UNIQUE,

    id_usuario NUMBER NOT NULL,

    CONSTRAINT fk_arbol_usuario
        FOREIGN KEY (id_usuario)
        REFERENCES usuarios(id_usuario)
);

CREATE SEQUENCE seq_arbol
START WITH 1
INCREMENT BY 1;

CREATE OR REPLACE TRIGGER trg_arbol
BEFORE INSERT ON arbol_binario
FOR EACH ROW
BEGIN
    :NEW.id_nodo := seq_arbol.NEXTVAL;
END;
/

CREATE TABLE hash_table (

    id_hash NUMBER PRIMARY KEY,

    posicion_hash NUMBER NOT NULL,

    id_usuario NUMBER NOT NULL,

    CONSTRAINT fk_hash_usuario
        FOREIGN KEY(id_usuario)
        REFERENCES usuarios(id_usuario)
);

CREATE SEQUENCE seq_hash
START WITH 1
INCREMENT BY 1;

CREATE OR REPLACE TRIGGER trg_hash
BEFORE INSERT ON hash_table
FOR EACH ROW
BEGIN
    :NEW.id_hash := seq_hash.NEXTVAL;
END;
/

select * from hash_table;

insert into rol(nombre_rol)
values('ADMIN');
insert into rol(nombre_rol)
values('CLIENTE');
insert into rol(nombre_rol)
values('REPARTIDOR');

commit;
update usuarios set id_rol = 1 where id_usuario = 1;
commit;
select * from arbol_binario;
select * from hash_table;
select * from usuarios;
select * from rol;
insert into usuarios(nombre, apellido, correo, pass, telefono, direccion, fecha_registro, id_rol)
values('Ruben', 'Paredes', 'rdparedes@gmail.com', '1234', '12345678', 'mi casa', TO_DATE('12/05/2026', 'DD/MM/YYYY'), 1);