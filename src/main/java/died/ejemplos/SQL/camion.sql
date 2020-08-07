--
-- PostgreSQL database dump
--

-- Dumped from database version 11.5
-- Dumped by pg_dump version 11.5

-- Started on 2020-08-06 23:03:02

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
-- TOC entry 2854 (class 0 OID 19941)
-- Dependencies: 203
-- Data for Name: camion; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.camion (id, patente, marca, modelo, km, costo_km, costo_hora, idplanta, fecha_compra) OVERRIDING SYSTEM VALUE VALUES (1, 'OVO666', 'Mercedez Ben', 'A42', '10.000 - 19.999', 10000.00, 100.00, NULL, '0020-07-10');


--
-- TOC entry 2852 (class 0 OID 19934)
-- Dependencies: 201
-- Data for Name: insumo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.insumo (idinsumo, nombre, descripcion, unidad_medida, costo, tipo, peso, densidad) OVERRIDING SYSTEM VALUE VALUES (1, 'ARENA', 'color amarillo', 'M3', 20.00, 'GENERAL', 1000.00, 0.00);
INSERT INTO public.insumo (idinsumo, nombre, descripcion, unidad_medida, costo, tipo, peso, densidad) OVERRIDING SYSTEM VALUE VALUES (2, 'AGUA', 'AGUA SABORIZADA', 'LITRO', 500.00, 'LIQUIDO', 0.00, 20.00);


--
-- TOC entry 2850 (class 0 OID 19927)
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
-- TOC entry 2856 (class 0 OID 19953)
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


--
-- TOC entry 2857 (class 0 OID 19968)
-- Dependencies: 206
-- Data for Name: stockinsumo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.stockinsumo (stock, puntoreposicion, idplanta, idinsumo) VALUES (500.00, 0.00, 1, 1);
INSERT INTO public.stockinsumo (stock, puntoreposicion, idplanta, idinsumo) VALUES (100.00, 0.00, 1, 2);
INSERT INTO public.stockinsumo (stock, puntoreposicion, idplanta, idinsumo) VALUES (100.00, 30.00, 2, 1);
INSERT INTO public.stockinsumo (stock, puntoreposicion, idplanta, idinsumo) VALUES (80.00, 20.00, 2, 2);
INSERT INTO public.stockinsumo (stock, puntoreposicion, idplanta, idinsumo) VALUES (20.00, 50.00, 3, 1);
INSERT INTO public.stockinsumo (stock, puntoreposicion, idplanta, idinsumo) VALUES (80.00, 50.00, 3, 2);
INSERT INTO public.stockinsumo (stock, puntoreposicion, idplanta, idinsumo) VALUES (90.00, 50.00, 4, 1);
INSERT INTO public.stockinsumo (stock, puntoreposicion, idplanta, idinsumo) VALUES (40.00, 50.00, 4, 2);
INSERT INTO public.stockinsumo (stock, puntoreposicion, idplanta, idinsumo) VALUES (100.00, 30.00, 5, 2);
INSERT INTO public.stockinsumo (stock, puntoreposicion, idplanta, idinsumo) VALUES (40.00, 20.00, 6, 1);
INSERT INTO public.stockinsumo (stock, puntoreposicion, idplanta, idinsumo) VALUES (70.00, 50.00, 6, 2);
INSERT INTO public.stockinsumo (stock, puntoreposicion, idplanta, idinsumo) VALUES (80.00, 50.00, 7, 1);
INSERT INTO public.stockinsumo (stock, puntoreposicion, idplanta, idinsumo) VALUES (60.00, 50.00, 7, 2);
INSERT INTO public.stockinsumo (stock, puntoreposicion, idplanta, idinsumo) VALUES (20.00, 30.00, 5, 1);


--
-- TOC entry 2863 (class 0 OID 0)
-- Dependencies: 202
-- Name: camion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.camion_id_seq', 1, true);


--
-- TOC entry 2864 (class 0 OID 0)
-- Dependencies: 196
-- Name: camion_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.camion_seq', 13, true);


--
-- TOC entry 2865 (class 0 OID 0)
-- Dependencies: 200
-- Name: insumo_idinsumo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.insumo_idinsumo_seq', 3, true);


--
-- TOC entry 2866 (class 0 OID 0)
-- Dependencies: 197
-- Name: insumo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.insumo_seq', 0, false);


--
-- TOC entry 2867 (class 0 OID 0)
-- Dependencies: 198
-- Name: planta_idplanta_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.planta_idplanta_seq', 7, true);


--
-- TOC entry 2868 (class 0 OID 0)
-- Dependencies: 204
-- Name: ruta_idruta_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ruta_idruta_seq', 8, true);


--
-- TOC entry 2716 (class 2606 OID 19945)
-- Name: camion camion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.camion
    ADD CONSTRAINT camion_pkey PRIMARY KEY (id);


--
-- TOC entry 2714 (class 2606 OID 19938)
-- Name: insumo insumo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.insumo
    ADD CONSTRAINT insumo_pkey PRIMARY KEY (idinsumo);


--
-- TOC entry 2712 (class 2606 OID 19931)
-- Name: planta planta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.planta
    ADD CONSTRAINT planta_pkey PRIMARY KEY (idplanta);


--
-- TOC entry 2718 (class 2606 OID 19957)
-- Name: ruta ruta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ruta
    ADD CONSTRAINT ruta_pkey PRIMARY KEY (idruta);


--
-- TOC entry 2720 (class 2606 OID 19972)
-- Name: stockinsumo stockinsumo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.stockinsumo
    ADD CONSTRAINT stockinsumo_pkey PRIMARY KEY (idplanta, idinsumo);


--
-- TOC entry 2721 (class 2606 OID 19946)
-- Name: camion camion_idplanta_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.camion
    ADD CONSTRAINT camion_idplanta_fkey FOREIGN KEY (idplanta) REFERENCES public.planta(idplanta) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2723 (class 2606 OID 19963)
-- Name: ruta ruta_idplantadestino_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ruta
    ADD CONSTRAINT ruta_idplantadestino_fkey FOREIGN KEY (idplantadestino) REFERENCES public.planta(idplanta) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2722 (class 2606 OID 19958)
-- Name: ruta ruta_idplantaorigen_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ruta
    ADD CONSTRAINT ruta_idplantaorigen_fkey FOREIGN KEY (idplantaorigen) REFERENCES public.planta(idplanta) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2725 (class 2606 OID 19978)
-- Name: stockinsumo stockinsumo_idinsumo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.stockinsumo
    ADD CONSTRAINT stockinsumo_idinsumo_fkey FOREIGN KEY (idinsumo) REFERENCES public.insumo(idinsumo) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2724 (class 2606 OID 19973)
-- Name: stockinsumo stockinsumo_idplanta_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.stockinsumo
    ADD CONSTRAINT stockinsumo_idplanta_fkey FOREIGN KEY (idplanta) REFERENCES public.planta(idplanta) ON UPDATE CASCADE ON DELETE CASCADE;


-- Completed on 2020-08-06 23:03:03

--
-- PostgreSQL database dump complete
--

