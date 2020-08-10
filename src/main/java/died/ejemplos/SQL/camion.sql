--
-- PostgreSQL database dump
--

-- Dumped from database version 11.5
-- Dumped by pg_dump version 11.5

-- Started on 2020-08-09 15:27:32

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
    idruta integer,
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
-- TOC entry 2842 (class 0 OID 19986)
-- Dependencies: 208
-- Data for Name: pedido; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pedido (idpedido, idplantaorigen, idplantadestino, idcamionasignado, fecha_solicitud, fecha_entrega, idruta, estado, costo) FROM stdin;
8	\N	3	\N	2020-08-07	2020-09-07	\N	CREADA	\N
6	\N	4	\N	2020-08-07	2020-09-07	\N	CREADA	\N
7	\N	5	\N	2020-08-07	2020-09-07	\N	CREADA	\N
9	\N	2	\N	2020-08-08	2020-08-18	\N	CREADA	\N
10	\N	6	\N	2020-08-08	2020-11-20	\N	CANCELADA	\N
11	6	6	1	2020-08-09	2020-08-18	\N	PROCESADA	\N
\.


--
-- TOC entry 2848 (class 0 OID 0)
-- Dependencies: 207
-- Name: pedido_idpedido_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pedido_idpedido_seq', 11, true);


--
-- TOC entry 2715 (class 2606 OID 19990)
-- Name: pedido pedido_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT pedido_pkey PRIMARY KEY (idpedido);


--
-- TOC entry 2718 (class 2606 OID 20001)
-- Name: pedido pedido_idcamionasignado_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT pedido_idcamionasignado_fkey FOREIGN KEY (idcamionasignado) REFERENCES public.camion(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2717 (class 2606 OID 19996)
-- Name: pedido pedido_idplantadestino_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT pedido_idplantadestino_fkey FOREIGN KEY (idplantadestino) REFERENCES public.planta(idplanta) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2716 (class 2606 OID 19991)
-- Name: pedido pedido_idplantaorigen_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT pedido_idplantaorigen_fkey FOREIGN KEY (idplantaorigen) REFERENCES public.planta(idplanta) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2719 (class 2606 OID 20006)
-- Name: pedido pedido_idruta_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT pedido_idruta_fkey FOREIGN KEY (idruta) REFERENCES public.ruta(idruta);


-- Completed on 2020-08-09 15:27:33

--
-- PostgreSQL database dump complete
--

