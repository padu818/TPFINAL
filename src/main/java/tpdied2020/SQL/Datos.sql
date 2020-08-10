--
-- PostgreSQL database dump
--

-- Dumped from database version 11.5
-- Dumped by pg_dump version 11.5

-- Started on 2020-08-10 11:10:52

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 203 (class 1259 OID 19941)
-- Name: camion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.camion (
    id integer NOT NULL,
    patente character varying(14) NOT NULL,
    marca character varying(45) NOT NULL,
    modelo character varying(45) NOT NULL,
    km character varying(45) NOT NULL,
    costo_km numeric(12,2),
    costo_hora numeric(12,2),
    idplanta integer,
    fecha_compra date NOT NULL
);


ALTER TABLE public.camion OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 19939)
-- Name: camion_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.camion ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.camion_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 196 (class 1259 OID 19826)
-- Name: camion_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.camion_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 1000000
    CACHE 1;


ALTER TABLE public.camion_seq OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 20011)
-- Name: detalleinsumosolicitado; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.detalleinsumosolicitado (
    idinsumo integer NOT NULL,
    idpedido integer NOT NULL,
    cantidad integer NOT NULL,
    precio integer NOT NULL
);


ALTER TABLE public.detalleinsumosolicitado OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 19934)
-- Name: insumo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.insumo (
    idinsumo integer NOT NULL,
    nombre character varying(45) NOT NULL,
    descripcion character varying(50) NOT NULL,
    unidad_medida character varying(12) NOT NULL,
    costo numeric(12,2),
    tipo character varying(45) NOT NULL,
    peso numeric(12,2),
    densidad numeric(12,2)
);


ALTER TABLE public.insumo OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 19932)
-- Name: insumo_idinsumo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.insumo ALTER COLUMN idinsumo ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.insumo_idinsumo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 197 (class 1259 OID 19852)
-- Name: insumo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.insumo_seq
    AS integer
    START WITH 0
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 19999999
    CACHE 1;


ALTER TABLE public.insumo_seq OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 19986)
-- Name: pedido; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pedido (
    idpedido integer NOT NULL,
    idplantaorigen integer,
    idplantadestino integer,
    idcamionasignado integer,
    fecha_solicitud date NOT NULL,
    fecha_entrega date NOT NULL,
    estado character varying(45),
    costo numeric(12,2)
);


ALTER TABLE public.pedido OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 19984)
-- Name: pedido_idpedido_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.pedido ALTER COLUMN idpedido ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.pedido_idpedido_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 199 (class 1259 OID 19927)
-- Name: planta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.planta (
    idplanta integer NOT NULL,
    nombre character varying(45) NOT NULL
);


ALTER TABLE public.planta OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 19925)
-- Name: planta_idplanta_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.planta ALTER COLUMN idplanta ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.planta_idplanta_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 205 (class 1259 OID 19953)
-- Name: ruta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ruta (
    idruta integer NOT NULL,
    duracionhs numeric(12,2),
    duracionkm numeric(12,2),
    cantmaxatransportar numeric(12,2),
    idplantaorigen integer,
    idplantadestino integer
);


ALTER TABLE public.ruta OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 19951)
-- Name: ruta_idruta_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.ruta ALTER COLUMN idruta ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.ruta_idruta_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 210 (class 1259 OID 20026)
-- Name: ruta_pedido; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ruta_pedido (
    idruta integer NOT NULL,
    idpedido integer NOT NULL
);


ALTER TABLE public.ruta_pedido OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 19968)
-- Name: stockinsumo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.stockinsumo (
    stock numeric(12,2),
    puntoreposicion numeric(12,2),
    idplanta integer NOT NULL,
    idinsumo integer NOT NULL
);


ALTER TABLE public.stockinsumo OWNER TO postgres;

--
-- TOC entry 2881 (class 0 OID 19941)
-- Dependencies: 203
-- Data for Name: camion; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.camion (id, patente, marca, modelo, km, costo_km, costo_hora, idplanta, fecha_compra) OVERRIDING SYSTEM VALUE VALUES (1, 'OVO666', 'Mercedez Ben', 'A42', '0 - 9.999', 100.00, 8000.00, 1, '2019-08-18');
INSERT INTO public.camion (id, patente, marca, modelo, km, costo_km, costo_hora, idplanta, fecha_compra) OVERRIDING SYSTEM VALUE VALUES (2, 'PAP777', 'Mercedez Ben', '8K40', '0 - 9.999', 80.00, 7000.00, 1, '2019-11-18');
INSERT INTO public.camion (id, patente, marca, modelo, km, costo_km, costo_hora, idplanta, fecha_compra) OVERRIDING SYSTEM VALUE VALUES (3, 'MMD333', 'Mercedez Ben', 'A40', '0 - 9.999', 70.00, 7500.00, 1, '2019-07-17');
INSERT INTO public.camion (id, patente, marca, modelo, km, costo_km, costo_hora, idplanta, fecha_compra) OVERRIDING SYSTEM VALUE VALUES (4, 'LLL456', 'Mercedez Ben', '40P', '0 - 9.999', 78.00, 9000.00, 1, '2018-08-17');


--
-- TOC entry 2887 (class 0 OID 20011)
-- Dependencies: 209
-- Data for Name: detalleinsumosolicitado; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.detalleinsumosolicitado (idinsumo, idpedido, cantidad, precio) VALUES (1, 6, 80, 1600);
INSERT INTO public.detalleinsumosolicitado (idinsumo, idpedido, cantidad, precio) VALUES (1, 7, 80, 1600);
INSERT INTO public.detalleinsumosolicitado (idinsumo, idpedido, cantidad, precio) VALUES (2, 7, 80, 40000);
INSERT INTO public.detalleinsumosolicitado (idinsumo, idpedido, cantidad, precio) VALUES (1, 8, 80, 1600);
INSERT INTO public.detalleinsumosolicitado (idinsumo, idpedido, cantidad, precio) VALUES (1, 9, 100, 2000);
INSERT INTO public.detalleinsumosolicitado (idinsumo, idpedido, cantidad, precio) VALUES (2, 9, 100, 50000);
INSERT INTO public.detalleinsumosolicitado (idinsumo, idpedido, cantidad, precio) VALUES (4, 9, 100, 5000);
INSERT INTO public.detalleinsumosolicitado (idinsumo, idpedido, cantidad, precio) VALUES (1, 10, 10, 200);
INSERT INTO public.detalleinsumosolicitado (idinsumo, idpedido, cantidad, precio) VALUES (2, 10, 10, 5000);
INSERT INTO public.detalleinsumosolicitado (idinsumo, idpedido, cantidad, precio) VALUES (4, 10, 1000, 50000);
INSERT INTO public.detalleinsumosolicitado (idinsumo, idpedido, cantidad, precio) VALUES (1, 11, 10, 200);
INSERT INTO public.detalleinsumosolicitado (idinsumo, idpedido, cantidad, precio) VALUES (1, 12, 40, 800);
INSERT INTO public.detalleinsumosolicitado (idinsumo, idpedido, cantidad, precio) VALUES (2, 12, 50, 25000);
INSERT INTO public.detalleinsumosolicitado (idinsumo, idpedido, cantidad, precio) VALUES (4, 12, 50, 2500);
INSERT INTO public.detalleinsumosolicitado (idinsumo, idpedido, cantidad, precio) VALUES (1, 13, 40, 800);
INSERT INTO public.detalleinsumosolicitado (idinsumo, idpedido, cantidad, precio) VALUES (2, 13, 20, 10000);
INSERT INTO public.detalleinsumosolicitado (idinsumo, idpedido, cantidad, precio) VALUES (4, 13, 100, 5000);
INSERT INTO public.detalleinsumosolicitado (idinsumo, idpedido, cantidad, precio) VALUES (2, 14, 100, 50000);
INSERT INTO public.detalleinsumosolicitado (idinsumo, idpedido, cantidad, precio) VALUES (4, 14, 100, 5000);
INSERT INTO public.detalleinsumosolicitado (idinsumo, idpedido, cantidad, precio) VALUES (1, 14, 100, 2000);
INSERT INTO public.detalleinsumosolicitado (idinsumo, idpedido, cantidad, precio) VALUES (1, 15, 20, 400);
INSERT INTO public.detalleinsumosolicitado (idinsumo, idpedido, cantidad, precio) VALUES (1, 16, 10, 200);
INSERT INTO public.detalleinsumosolicitado (idinsumo, idpedido, cantidad, precio) VALUES (1, 17, 80, 1600);
INSERT INTO public.detalleinsumosolicitado (idinsumo, idpedido, cantidad, precio) VALUES (1, 18, 80, 1600);
INSERT INTO public.detalleinsumosolicitado (idinsumo, idpedido, cantidad, precio) VALUES (1, 19, 80, 1600);
INSERT INTO public.detalleinsumosolicitado (idinsumo, idpedido, cantidad, precio) VALUES (1, 20, 80, 1600);


--
-- TOC entry 2879 (class 0 OID 19934)
-- Dependencies: 201
-- Data for Name: insumo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.insumo (idinsumo, nombre, descripcion, unidad_medida, costo, tipo, peso, densidad) OVERRIDING SYSTEM VALUE VALUES (1, 'ARENA', 'color amarillo', 'M3', 20.00, 'GENERAL', 1000.00, 0.00);
INSERT INTO public.insumo (idinsumo, nombre, descripcion, unidad_medida, costo, tipo, peso, densidad) OVERRIDING SYSTEM VALUE VALUES (2, 'AGUA', 'AGUA SABORIZADA', 'LITRO', 500.00, 'LIQUIDO', 0.00, 20.00);
INSERT INTO public.insumo (idinsumo, nombre, descripcion, unidad_medida, costo, tipo, peso, densidad) OVERRIDING SYSTEM VALUE VALUES (4, 'arroz', 'czcx', 'KILO', 50.00, 'GENERAL', 1.00, 0.00);


--
-- TOC entry 2886 (class 0 OID 19986)
-- Dependencies: 208
-- Data for Name: pedido; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.pedido (idpedido, idplantaorigen, idplantadestino, idcamionasignado, fecha_solicitud, fecha_entrega, estado, costo) OVERRIDING SYSTEM VALUE VALUES (7, NULL, 5, NULL, '2020-08-07', '2020-09-07', 'CREADA', NULL);
INSERT INTO public.pedido (idpedido, idplantaorigen, idplantadestino, idcamionasignado, fecha_solicitud, fecha_entrega, estado, costo) OVERRIDING SYSTEM VALUE VALUES (10, NULL, 6, NULL, '2020-08-08', '2020-11-20', 'CANCELADA', NULL);
INSERT INTO public.pedido (idpedido, idplantaorigen, idplantadestino, idcamionasignado, fecha_solicitud, fecha_entrega, estado, costo) OVERRIDING SYSTEM VALUE VALUES (6, 1, 4, 4, '2020-08-07', '2020-09-07', 'PROCESADA', 36000.00);
INSERT INTO public.pedido (idpedido, idplantaorigen, idplantadestino, idcamionasignado, fecha_solicitud, fecha_entrega, estado, costo) OVERRIDING SYSTEM VALUE VALUES (9, 1, 2, 3, '2020-08-08', '2020-08-18', 'PROCESADA', 17500.00);
INSERT INTO public.pedido (idpedido, idplantaorigen, idplantadestino, idcamionasignado, fecha_solicitud, fecha_entrega, estado, costo) OVERRIDING SYSTEM VALUE VALUES (11, 6, 6, 1, '2020-08-09', '2020-08-18', 'ENTREGADA', 0.00);
INSERT INTO public.pedido (idpedido, idplantaorigen, idplantadestino, idcamionasignado, fecha_solicitud, fecha_entrega, estado, costo) OVERRIDING SYSTEM VALUE VALUES (12, 1, 1, 1, '2020-08-09', '2020-08-18', 'ENTREGADA', 32000.00);
INSERT INTO public.pedido (idpedido, idplantaorigen, idplantadestino, idcamionasignado, fecha_solicitud, fecha_entrega, estado, costo) OVERRIDING SYSTEM VALUE VALUES (13, 1, 1, 1, '2020-08-09', '2020-08-18', 'ENTREGADA', 40000.00);
INSERT INTO public.pedido (idpedido, idplantaorigen, idplantadestino, idcamionasignado, fecha_solicitud, fecha_entrega, estado, costo) OVERRIDING SYSTEM VALUE VALUES (15, 1, 6, 4, '2020-08-09', '2020-08-18', 'PROCESADA', 62400.00);
INSERT INTO public.pedido (idpedido, idplantaorigen, idplantadestino, idcamionasignado, fecha_solicitud, fecha_entrega, estado, costo) OVERRIDING SYSTEM VALUE VALUES (14, 1, 1, 1, '2020-08-09', '2020-08-18', 'ENTREGADA', 40000.00);
INSERT INTO public.pedido (idpedido, idplantaorigen, idplantadestino, idcamionasignado, fecha_solicitud, fecha_entrega, estado, costo) OVERRIDING SYSTEM VALUE VALUES (8, 1, 1, 4, '2020-08-07', '2020-09-07', 'ENTREGADA', 15600.00);
INSERT INTO public.pedido (idpedido, idplantaorigen, idplantadestino, idcamionasignado, fecha_solicitud, fecha_entrega, estado, costo) OVERRIDING SYSTEM VALUE VALUES (16, 1, 1, 1, '2020-08-09', '2020-08-18', 'ENTREGADA', 20000.00);
INSERT INTO public.pedido (idpedido, idplantaorigen, idplantadestino, idcamionasignado, fecha_solicitud, fecha_entrega, estado, costo) OVERRIDING SYSTEM VALUE VALUES (17, 1, 1, 4, '2020-08-09', '2020-08-18', 'ENTREGADA', 18000.00);
INSERT INTO public.pedido (idpedido, idplantaorigen, idplantadestino, idcamionasignado, fecha_solicitud, fecha_entrega, estado, costo) OVERRIDING SYSTEM VALUE VALUES (18, 1, 1, 1, '2020-08-09', '2020-08-18', 'ENTREGADA', 16000.00);
INSERT INTO public.pedido (idpedido, idplantaorigen, idplantadestino, idcamionasignado, fecha_solicitud, fecha_entrega, estado, costo) OVERRIDING SYSTEM VALUE VALUES (19, 1, 3, 1, '2020-08-09', '2021-02-18', 'ENTREGADA', 16000.00);
INSERT INTO public.pedido (idpedido, idplantaorigen, idplantadestino, idcamionasignado, fecha_solicitud, fecha_entrega, estado, costo) OVERRIDING SYSTEM VALUE VALUES (20, 1, 2, 1, '2020-08-09', '2020-08-18', 'ENTREGADA', 24000.00);


--
-- TOC entry 2877 (class 0 OID 19927)
-- Dependencies: 199
-- Data for Name: planta; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.planta (idplanta, nombre) OVERRIDING SYSTEM VALUE VALUES (1, 'Acopio Puerto');
INSERT INTO public.planta (idplanta, nombre) OVERRIDING SYSTEM VALUE VALUES (2, 'Planta 1');
INSERT INTO public.planta (idplanta, nombre) OVERRIDING SYSTEM VALUE VALUES (3, 'Planta 2');
INSERT INTO public.planta (idplanta, nombre) OVERRIDING SYSTEM VALUE VALUES (4, 'Planta 3');
INSERT INTO public.planta (idplanta, nombre) OVERRIDING SYSTEM VALUE VALUES (5, 'Planta 4');
INSERT INTO public.planta (idplanta, nombre) OVERRIDING SYSTEM VALUE VALUES (6, 'Planta 5');
INSERT INTO public.planta (idplanta, nombre) OVERRIDING SYSTEM VALUE VALUES (7, 'Acopio Final');


--
-- TOC entry 2883 (class 0 OID 19953)
-- Dependencies: 205
-- Data for Name: ruta; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.ruta (idruta, duracionhs, duracionkm, cantmaxatransportar, idplantaorigen, idplantadestino) OVERRIDING SYSTEM VALUE VALUES (1, 3.00, 300.00, 10000.00, 1, 2);
INSERT INTO public.ruta (idruta, duracionhs, duracionkm, cantmaxatransportar, idplantaorigen, idplantadestino) OVERRIDING SYSTEM VALUE VALUES (2, 2.00, 200.00, 5000.00, 1, 3);
INSERT INTO public.ruta (idruta, duracionhs, duracionkm, cantmaxatransportar, idplantaorigen, idplantadestino) OVERRIDING SYSTEM VALUE VALUES (3, 2.00, 200.00, 7000.00, 3, 4);
INSERT INTO public.ruta (idruta, duracionhs, duracionkm, cantmaxatransportar, idplantaorigen, idplantadestino) OVERRIDING SYSTEM VALUE VALUES (4, 6.00, 600.00, 8000.00, 3, 6);
INSERT INTO public.ruta (idruta, duracionhs, duracionkm, cantmaxatransportar, idplantaorigen, idplantadestino) OVERRIDING SYSTEM VALUE VALUES (5, 5.00, 500.00, 10000.00, 2, 5);
INSERT INTO public.ruta (idruta, duracionhs, duracionkm, cantmaxatransportar, idplantaorigen, idplantadestino) OVERRIDING SYSTEM VALUE VALUES (6, 4.00, 400.00, 2000.00, 5, 6);
INSERT INTO public.ruta (idruta, duracionhs, duracionkm, cantmaxatransportar, idplantaorigen, idplantadestino) OVERRIDING SYSTEM VALUE VALUES (7, 2.00, 200.00, 8000.00, 5, 7);
INSERT INTO public.ruta (idruta, duracionhs, duracionkm, cantmaxatransportar, idplantaorigen, idplantadestino) OVERRIDING SYSTEM VALUE VALUES (8, 2.00, 200.00, 8000.00, 6, 7);
INSERT INTO public.ruta (idruta, duracionhs, duracionkm, cantmaxatransportar, idplantaorigen, idplantadestino) OVERRIDING SYSTEM VALUE VALUES (14, 3.00, 300.00, 5000.00, 4, 5);


--
-- TOC entry 2888 (class 0 OID 20026)
-- Dependencies: 210
-- Data for Name: ruta_pedido; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2884 (class 0 OID 19968)
-- Dependencies: 206
-- Data for Name: stockinsumo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.stockinsumo (stock, puntoreposicion, idplanta, idinsumo) VALUES (80.00, 50.00, 3, 2);
INSERT INTO public.stockinsumo (stock, puntoreposicion, idplanta, idinsumo) VALUES (40.00, 50.00, 4, 2);
INSERT INTO public.stockinsumo (stock, puntoreposicion, idplanta, idinsumo) VALUES (40.00, 20.00, 6, 1);
INSERT INTO public.stockinsumo (stock, puntoreposicion, idplanta, idinsumo) VALUES (70.00, 50.00, 6, 2);
INSERT INTO public.stockinsumo (stock, puntoreposicion, idplanta, idinsumo) VALUES (20.00, 30.00, 5, 1);
INSERT INTO public.stockinsumo (stock, puntoreposicion, idplanta, idinsumo) VALUES (10.00, 50.00, 4, 1);
INSERT INTO public.stockinsumo (stock, puntoreposicion, idplanta, idinsumo) VALUES (200.00, 50.00, 2, 4);
INSERT INTO public.stockinsumo (stock, puntoreposicion, idplanta, idinsumo) VALUES (50.00, 40.00, 3, 4);
INSERT INTO public.stockinsumo (stock, puntoreposicion, idplanta, idinsumo) VALUES (10.00, 50.00, 4, 4);
INSERT INTO public.stockinsumo (stock, puntoreposicion, idplanta, idinsumo) VALUES (20.00, 50.00, 5, 4);
INSERT INTO public.stockinsumo (stock, puntoreposicion, idplanta, idinsumo) VALUES (40.00, 50.00, 6, 4);
INSERT INTO public.stockinsumo (stock, puntoreposicion, idplanta, idinsumo) VALUES (100.00, 30.00, 7, 4);
INSERT INTO public.stockinsumo (stock, puntoreposicion, idplanta, idinsumo) VALUES (20.00, 50.00, 5, 2);
INSERT INTO public.stockinsumo (stock, puntoreposicion, idplanta, idinsumo) VALUES (200.00, 50.00, 2, 2);
INSERT INTO public.stockinsumo (stock, puntoreposicion, idplanta, idinsumo) VALUES (200.00, 50.00, 7, 2);
INSERT INTO public.stockinsumo (stock, puntoreposicion, idplanta, idinsumo) VALUES (150.00, 70.00, 7, 1);
INSERT INTO public.stockinsumo (stock, puntoreposicion, idplanta, idinsumo) VALUES (200.00, 0.00, 1, 2);
INSERT INTO public.stockinsumo (stock, puntoreposicion, idplanta, idinsumo) VALUES (200.00, 50.00, 1, 4);
INSERT INTO public.stockinsumo (stock, puntoreposicion, idplanta, idinsumo) VALUES (850.00, 0.00, 1, 1);
INSERT INTO public.stockinsumo (stock, puntoreposicion, idplanta, idinsumo) VALUES (100.00, 50.00, 3, 1);
INSERT INTO public.stockinsumo (stock, puntoreposicion, idplanta, idinsumo) VALUES (180.00, 30.00, 2, 1);


--
-- TOC entry 2894 (class 0 OID 0)
-- Dependencies: 202
-- Name: camion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.camion_id_seq', 4, true);


--
-- TOC entry 2895 (class 0 OID 0)
-- Dependencies: 196
-- Name: camion_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.camion_seq', 13, true);


--
-- TOC entry 2896 (class 0 OID 0)
-- Dependencies: 200
-- Name: insumo_idinsumo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.insumo_idinsumo_seq', 4, true);


--
-- TOC entry 2897 (class 0 OID 0)
-- Dependencies: 197
-- Name: insumo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.insumo_seq', 0, false);


--
-- TOC entry 2898 (class 0 OID 0)
-- Dependencies: 207
-- Name: pedido_idpedido_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pedido_idpedido_seq', 20, true);


--
-- TOC entry 2899 (class 0 OID 0)
-- Dependencies: 198
-- Name: planta_idplanta_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.planta_idplanta_seq', 7, true);


--
-- TOC entry 2900 (class 0 OID 0)
-- Dependencies: 204
-- Name: ruta_idruta_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ruta_idruta_seq', 14, true);


--
-- TOC entry 2730 (class 2606 OID 19945)
-- Name: camion camion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.camion
    ADD CONSTRAINT camion_pkey PRIMARY KEY (id);


--
-- TOC entry 2738 (class 2606 OID 20015)
-- Name: detalleinsumosolicitado detalleinsumosolicitado_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detalleinsumosolicitado
    ADD CONSTRAINT detalleinsumosolicitado_pkey PRIMARY KEY (idinsumo, idpedido);


--
-- TOC entry 2728 (class 2606 OID 19938)
-- Name: insumo insumo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.insumo
    ADD CONSTRAINT insumo_pkey PRIMARY KEY (idinsumo);


--
-- TOC entry 2736 (class 2606 OID 19990)
-- Name: pedido pedido_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT pedido_pkey PRIMARY KEY (idpedido);


--
-- TOC entry 2726 (class 2606 OID 19931)
-- Name: planta planta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.planta
    ADD CONSTRAINT planta_pkey PRIMARY KEY (idplanta);


--
-- TOC entry 2740 (class 2606 OID 20030)
-- Name: ruta_pedido ruta_pedido_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ruta_pedido
    ADD CONSTRAINT ruta_pedido_pkey PRIMARY KEY (idruta, idpedido);


--
-- TOC entry 2732 (class 2606 OID 19957)
-- Name: ruta ruta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ruta
    ADD CONSTRAINT ruta_pkey PRIMARY KEY (idruta);


--
-- TOC entry 2734 (class 2606 OID 19972)
-- Name: stockinsumo stockinsumo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.stockinsumo
    ADD CONSTRAINT stockinsumo_pkey PRIMARY KEY (idplanta, idinsumo);


--
-- TOC entry 2741 (class 2606 OID 19946)
-- Name: camion camion_idplanta_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.camion
    ADD CONSTRAINT camion_idplanta_fkey FOREIGN KEY (idplanta) REFERENCES public.planta(idplanta) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2749 (class 2606 OID 20016)
-- Name: detalleinsumosolicitado detalleinsumosolicitado_idinsumo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detalleinsumosolicitado
    ADD CONSTRAINT detalleinsumosolicitado_idinsumo_fkey FOREIGN KEY (idinsumo) REFERENCES public.insumo(idinsumo) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2750 (class 2606 OID 20021)
-- Name: detalleinsumosolicitado detalleinsumosolicitado_idpedido_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detalleinsumosolicitado
    ADD CONSTRAINT detalleinsumosolicitado_idpedido_fkey FOREIGN KEY (idpedido) REFERENCES public.pedido(idpedido) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2748 (class 2606 OID 20001)
-- Name: pedido pedido_idcamionasignado_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT pedido_idcamionasignado_fkey FOREIGN KEY (idcamionasignado) REFERENCES public.camion(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2747 (class 2606 OID 19996)
-- Name: pedido pedido_idplantadestino_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT pedido_idplantadestino_fkey FOREIGN KEY (idplantadestino) REFERENCES public.planta(idplanta) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2746 (class 2606 OID 19991)
-- Name: pedido pedido_idplantaorigen_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT pedido_idplantaorigen_fkey FOREIGN KEY (idplantaorigen) REFERENCES public.planta(idplanta) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2743 (class 2606 OID 19963)
-- Name: ruta ruta_idplantadestino_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ruta
    ADD CONSTRAINT ruta_idplantadestino_fkey FOREIGN KEY (idplantadestino) REFERENCES public.planta(idplanta) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2742 (class 2606 OID 19958)
-- Name: ruta ruta_idplantaorigen_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ruta
    ADD CONSTRAINT ruta_idplantaorigen_fkey FOREIGN KEY (idplantaorigen) REFERENCES public.planta(idplanta) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2752 (class 2606 OID 20036)
-- Name: ruta_pedido ruta_pedido_idpedido_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ruta_pedido
    ADD CONSTRAINT ruta_pedido_idpedido_fkey FOREIGN KEY (idpedido) REFERENCES public.pedido(idpedido) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2751 (class 2606 OID 20031)
-- Name: ruta_pedido ruta_pedido_idruta_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ruta_pedido
    ADD CONSTRAINT ruta_pedido_idruta_fkey FOREIGN KEY (idruta) REFERENCES public.ruta(idruta) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2745 (class 2606 OID 19978)
-- Name: stockinsumo stockinsumo_idinsumo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.stockinsumo
    ADD CONSTRAINT stockinsumo_idinsumo_fkey FOREIGN KEY (idinsumo) REFERENCES public.insumo(idinsumo) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2744 (class 2606 OID 19973)
-- Name: stockinsumo stockinsumo_idplanta_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.stockinsumo
    ADD CONSTRAINT stockinsumo_idplanta_fkey FOREIGN KEY (idplanta) REFERENCES public.planta(idplanta) ON UPDATE CASCADE ON DELETE CASCADE;


-- Completed on 2020-08-10 11:10:52

--
-- PostgreSQL database dump complete
--

