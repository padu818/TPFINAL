--
-- PostgreSQL database dump
--

-- Dumped from database version 11.5
-- Dumped by pg_dump version 11.5

-- Started on 2020-08-09 22:15:15

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
-- TOC entry 2844 (class 0 OID 19986)
-- Dependencies: 208
-- Data for Name: pedido; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.pedido (idpedido, idplantaorigen, idplantadestino, idcamionasignado, fecha_solicitud, fecha_entrega, estado, costo) OVERRIDING SYSTEM VALUE VALUES (7, NULL, 5, NULL, '2020-08-07', '2020-09-07', 'CREADA', NULL);
INSERT INTO public.pedido (idpedido, idplantaorigen, idplantadestino, idcamionasignado, fecha_solicitud, fecha_entrega, estado, costo) OVERRIDING SYSTEM VALUE VALUES (10, NULL, 6, NULL, '2020-08-08', '2020-11-20', 'CANCELADA', NULL);
INSERT INTO public.pedido (idpedido, idplantaorigen, idplantadestino, idcamionasignado, fecha_solicitud, fecha_entrega, estado, costo) OVERRIDING SYSTEM VALUE VALUES (8, 1, 3, 4, '2020-08-07', '2020-09-07', 'PROCESADA', 15600.00);
INSERT INTO public.pedido (idpedido, idplantaorigen, idplantadestino, idcamionasignado, fecha_solicitud, fecha_entrega, estado, costo) OVERRIDING SYSTEM VALUE VALUES (6, 1, 4, 4, '2020-08-07', '2020-09-07', 'PROCESADA', 36000.00);
INSERT INTO public.pedido (idpedido, idplantaorigen, idplantadestino, idcamionasignado, fecha_solicitud, fecha_entrega, estado, costo) OVERRIDING SYSTEM VALUE VALUES (9, 1, 2, 3, '2020-08-08', '2020-08-18', 'PROCESADA', 17500.00);
INSERT INTO public.pedido (idpedido, idplantaorigen, idplantadestino, idcamionasignado, fecha_solicitud, fecha_entrega, estado, costo) OVERRIDING SYSTEM VALUE VALUES (11, 6, 6, 1, '2020-08-09', '2020-08-18', 'ENTREGADA', 0.00);


--
-- TOC entry 2850 (class 0 OID 0)
-- Dependencies: 207
-- Name: pedido_idpedido_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pedido_idpedido_seq', 11, true);


--
-- TOC entry 2718 (class 2606 OID 19990)
-- Name: pedido pedido_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT pedido_pkey PRIMARY KEY (idpedido);


--
-- TOC entry 2721 (class 2606 OID 20001)
-- Name: pedido pedido_idcamionasignado_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT pedido_idcamionasignado_fkey FOREIGN KEY (idcamionasignado) REFERENCES public.camion(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2720 (class 2606 OID 19996)
-- Name: pedido pedido_idplantadestino_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT pedido_idplantadestino_fkey FOREIGN KEY (idplantadestino) REFERENCES public.planta(idplanta) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2719 (class 2606 OID 19991)
-- Name: pedido pedido_idplantaorigen_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT pedido_idplantaorigen_fkey FOREIGN KEY (idplantaorigen) REFERENCES public.planta(idplanta) ON UPDATE CASCADE ON DELETE CASCADE;


-- Completed on 2020-08-09 22:15:15

--
-- PostgreSQL database dump complete
--

