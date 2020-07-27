--
-- PostgreSQL database dump
--

-- Dumped from database version 11.5
-- Dumped by pg_dump version 11.5

-- Started on 2020-07-25 00:02:42

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

--
-- TOC entry 197 (class 1259 OID 19826)
-- Name: camion_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.camion_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 1000000
    CACHE 1;


ALTER TABLE public.camion_seq OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 196 (class 1259 OID 19821)
-- Name: camion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.camion (
    id integer DEFAULT nextval('public.camion_seq'::regclass) NOT NULL,
    patente character varying(14),
    marca character varying(45),
    modelo character varying(45),
    km character varying(45),
    costo_km numeric(12,2),
    costo_hora numeric(12,2),
    fecha_compra date
);


ALTER TABLE public.camion OWNER TO postgres;

--
-- TOC entry 2809 (class 0 OID 19821)
-- Dependencies: 196
-- Data for Name: camion; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.camion (id, patente, marca, modelo, km, costo_km, costo_hora, fecha_compra) VALUES (1, 'ovo666', 'hgjhgjh', 'fgfgf', '40', NULL, NULL, NULL);
INSERT INTO public.camion (id, patente, marca, modelo, km, costo_km, costo_hora, fecha_compra) VALUES (2, 'hhh888', 'hjghjh', 'hgfghg', '400', NULL, NULL, NULL);
INSERT INTO public.camion (id, patente, marca, modelo, km, costo_km, costo_hora, fecha_compra) VALUES (3, 'ovo898', 'ASTRAL', 'BM 21', '40000', NULL, NULL, NULL);


--
-- TOC entry 2816 (class 0 OID 0)
-- Dependencies: 197
-- Name: camion_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.camion_seq', 3, true);


--
-- TOC entry 2687 (class 2606 OID 19825)
-- Name: camion camion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.camion
    ADD CONSTRAINT camion_pkey PRIMARY KEY (id);


-- Completed on 2020-07-25 00:02:43

--
-- PostgreSQL database dump complete
--

