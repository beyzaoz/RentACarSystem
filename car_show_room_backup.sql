--
-- PostgreSQL database dump
--

-- Dumped from database version 17.4
-- Dumped by pg_dump version 17.4

-- Started on 2025-09-28 11:45:34

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 231 (class 1259 OID 33172)
-- Name: vehicle_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.vehicle_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.vehicle_seq OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 218 (class 1259 OID 33008)
-- Name: car; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.car (
    id integer DEFAULT nextval('public.vehicle_seq'::regclass) NOT NULL,
    vehicleplatenumber character varying(150) NOT NULL,
    createddate date DEFAULT CURRENT_DATE,
    updateddate date DEFAULT CURRENT_DATE,
    vehicle_model character varying(150) NOT NULL,
    vehicletype character varying(50),
    hourlyprice numeric(10,2),
    dailyprice numeric(10,2),
    weeklyprice numeric(10,2) NOT NULL,
    monthlyprice numeric(10,2),
    vehicleavailable character varying(50) DEFAULT 'AVAILABLE'::character varying,
    created_by integer,
    updated_by integer,
    vehicle_price numeric(10,2) DEFAULT 1000000.00 NOT NULL
);


ALTER TABLE public.car OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 33007)
-- Name: car_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.car_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.car_id_seq OWNER TO postgres;

--
-- TOC entry 4987 (class 0 OID 0)
-- Dependencies: 217
-- Name: car_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.car_id_seq OWNED BY public.car.id;


--
-- TOC entry 220 (class 1259 OID 33017)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id integer NOT NULL,
    first_name character varying(150) NOT NULL,
    last_name character varying(150) NOT NULL,
    email character varying(50) NOT NULL,
    password character varying(50) NOT NULL,
    driver_license character varying(50) NOT NULL,
    birth_date date NOT NULL,
    createddate date DEFAULT CURRENT_DATE,
    updateddate date DEFAULT CURRENT_DATE,
    roles character varying(50) NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 33016)
-- Name: customer_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.customer_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.customer_id_seq OWNER TO postgres;

--
-- TOC entry 4988 (class 0 OID 0)
-- Dependencies: 219
-- Name: customer_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.customer_id_seq OWNED BY public.users.id;


--
-- TOC entry 228 (class 1259 OID 33080)
-- Name: helicopter; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.helicopter (
    id integer DEFAULT nextval('public.vehicle_seq'::regclass) NOT NULL,
    vehicletype character varying(50) NOT NULL,
    vehicleavailable character varying(50) NOT NULL,
    serialnumber character varying(50) NOT NULL,
    purpose character varying(50) NOT NULL,
    dailyprice numeric(10,2) NOT NULL,
    hourlyprice numeric(10,2) NOT NULL,
    weeklyprice numeric(10,2) NOT NULL,
    monthlyprice numeric(10,2) NOT NULL,
    createddate date DEFAULT CURRENT_DATE,
    updateddate date DEFAULT CURRENT_DATE,
    updated_by integer,
    created_by integer,
    vehicle_model character varying(255),
    vehicle_price numeric(10,2) DEFAULT 1000000.00 NOT NULL
);


ALTER TABLE public.helicopter OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 33079)
-- Name: helicopter_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.helicopter_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.helicopter_id_seq OWNER TO postgres;

--
-- TOC entry 4989 (class 0 OID 0)
-- Dependencies: 227
-- Name: helicopter_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.helicopter_id_seq OWNED BY public.helicopter.id;


--
-- TOC entry 230 (class 1259 OID 33090)
-- Name: motorcycle; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.motorcycle (
    id integer DEFAULT nextval('public.vehicle_seq'::regclass) NOT NULL,
    vehicletype character varying(50) NOT NULL,
    vehicleavailable character varying(50) NOT NULL,
    motortype character varying(50) NOT NULL,
    mplatenumber character varying(50) NOT NULL,
    dailyprice numeric(10,2) NOT NULL,
    hourlyprice numeric(10,2) NOT NULL,
    weeklyprice numeric(10,2) NOT NULL,
    monthlyprice numeric(10,2) NOT NULL,
    createddate date DEFAULT CURRENT_DATE,
    updateddate date DEFAULT CURRENT_DATE,
    updated_by integer,
    created_by integer,
    vehicle_model character varying(50),
    vehicle_price numeric(10,2) DEFAULT 1000000.00 NOT NULL
);


ALTER TABLE public.motorcycle OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 33089)
-- Name: motorcycle_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.motorcycle_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.motorcycle_id_seq OWNER TO postgres;

--
-- TOC entry 4990 (class 0 OID 0)
-- Dependencies: 229
-- Name: motorcycle_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.motorcycle_id_seq OWNED BY public.motorcycle.id;


--
-- TOC entry 224 (class 1259 OID 33045)
-- Name: payment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.payment (
    id integer NOT NULL,
    reservation_id integer,
    payment_date date,
    total_amount numeric(10,2),
    payment_method character varying(50),
    payment_status character varying(50),
    createddate date DEFAULT CURRENT_DATE,
    updateddate date DEFAULT CURRENT_DATE
);


ALTER TABLE public.payment OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 33044)
-- Name: payment_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.payment_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.payment_id_seq OWNER TO postgres;

--
-- TOC entry 4991 (class 0 OID 0)
-- Dependencies: 223
-- Name: payment_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.payment_id_seq OWNED BY public.payment.id;


--
-- TOC entry 222 (class 1259 OID 33026)
-- Name: reservation; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reservation (
    id integer NOT NULL,
    customer_id integer,
    vehicle_id integer,
    start_date_time date,
    end_date_time date,
    orderdate date DEFAULT CURRENT_DATE,
    total_amount numeric(10,2),
    status character varying(20) DEFAULT 'ACTIVE'::character varying
);


ALTER TABLE public.reservation OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 33025)
-- Name: reservation_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.reservation_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.reservation_id_seq OWNER TO postgres;

--
-- TOC entry 4992 (class 0 OID 0)
-- Dependencies: 221
-- Name: reservation_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.reservation_id_seq OWNED BY public.reservation.id;


--
-- TOC entry 226 (class 1259 OID 33071)
-- Name: vehicle; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.vehicle (
    id integer NOT NULL,
    vehicletype character varying(50) NOT NULL,
    vehicleavailable character varying(50) NOT NULL,
    dailyprice numeric(10,2) NOT NULL,
    hourlyprice numeric(10,2) NOT NULL,
    weeklyprice numeric(10,2) NOT NULL,
    monthlyprice numeric(10,2) NOT NULL,
    createddate date DEFAULT CURRENT_DATE,
    updateddate date DEFAULT CURRENT_DATE,
    vehiclebrand character varying(50) NOT NULL,
    created_by integer,
    updated_by integer
);


ALTER TABLE public.vehicle OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 33070)
-- Name: vehicle_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.vehicle_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.vehicle_id_seq OWNER TO postgres;

--
-- TOC entry 4993 (class 0 OID 0)
-- Dependencies: 225
-- Name: vehicle_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.vehicle_id_seq OWNED BY public.vehicle.id;


--
-- TOC entry 4784 (class 2604 OID 33048)
-- Name: payment id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payment ALTER COLUMN id SET DEFAULT nextval('public.payment_id_seq'::regclass);


--
-- TOC entry 4781 (class 2604 OID 33029)
-- Name: reservation id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reservation ALTER COLUMN id SET DEFAULT nextval('public.reservation_id_seq'::regclass);


--
-- TOC entry 4778 (class 2604 OID 33020)
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.customer_id_seq'::regclass);


--
-- TOC entry 4787 (class 2604 OID 33074)
-- Name: vehicle id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vehicle ALTER COLUMN id SET DEFAULT nextval('public.vehicle_id_seq'::regclass);


--
-- TOC entry 4968 (class 0 OID 33008)
-- Dependencies: 218
-- Data for Name: car; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.car (id, vehicleplatenumber, createddate, updateddate, vehicle_model, vehicletype, hourlyprice, dailyprice, weeklyprice, monthlyprice, vehicleavailable, created_by, updated_by, vehicle_price) FROM stdin;
2	34ABC678	2025-09-27	2025-09-27	Toyota	Car	34.00	125.00	655.80	1456.89	AVAILABLE	10	10	1700000.00
3	34SD1234	2025-09-27	2025-09-27	Tesla	Car	45.00	136.80	790.00	1755.05	AVAILABLE	10	10	2300000.00
\.


--
-- TOC entry 4978 (class 0 OID 33080)
-- Dependencies: 228
-- Data for Name: helicopter; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.helicopter (id, vehicletype, vehicleavailable, serialnumber, purpose, dailyprice, hourlyprice, weeklyprice, monthlyprice, createddate, updateddate, updated_by, created_by, vehicle_model, vehicle_price) FROM stdin;
4	Helicopter	AVAILABLE	ASD3456	private use	1240.05	450.00	5750.90	10250.65	2025-09-27	2025-09-27	10	10	Bell 206 Jetranger	42300000.00
5	Helicopter	AVAILABLE	EXP537596	transportation	12000.00	3116.80	34570.00	70567.50	2025-09-27	2025-09-27	10	10	Sikorsky UH-60 Black Hawk	18300000.00
\.


--
-- TOC entry 4980 (class 0 OID 33090)
-- Dependencies: 230
-- Data for Name: motorcycle; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.motorcycle (id, vehicletype, vehicleavailable, motortype, mplatenumber, dailyprice, hourlyprice, weeklyprice, monthlyprice, createddate, updateddate, updated_by, created_by, vehicle_model, vehicle_price) FROM stdin;
7	motorcycle	AVAILABLE	736 cc	35BVD765	136.80	43.00	456.98	1145.32	2025-09-27	2025-09-27	10	10	Honda CB750	30000.00
6	Motorcycle	AVAILABLE	399 cc	33TCD567	120.98	20.50	789.98	1356.99	2025-09-27	2025-09-27	10	10	Kawasaki Ninja 400	16000.00
\.


--
-- TOC entry 4974 (class 0 OID 33045)
-- Dependencies: 224
-- Data for Name: payment; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.payment (id, reservation_id, payment_date, total_amount, payment_method, payment_status, createddate, updateddate) FROM stdin;
\.


--
-- TOC entry 4972 (class 0 OID 33026)
-- Dependencies: 222
-- Data for Name: reservation; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.reservation (id, customer_id, vehicle_id, start_date_time, end_date_time, orderdate, total_amount, status) FROM stdin;
10	12	5	2025-09-30	2025-10-30	2025-09-28	70567.50	CANCELED
11	12	6	2025-09-30	2025-10-30	2025-09-28	1356.99	ACTIVE
12	12	3	2025-09-30	2025-10-30	2025-09-28	1755.05	ACTIVE
13	12	5	2025-09-30	2025-10-30	2025-09-28	70567.50	ACTIVE
\.


--
-- TOC entry 4970 (class 0 OID 33017)
-- Dependencies: 220
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, first_name, last_name, email, password, driver_license, birth_date, createddate, updateddate, roles) FROM stdin;
10	Beyza	Pat	beyza@patika.com	00	1234567ABCD	1998-08-09	2025-09-27	2025-09-27	ADMIN
11	Ali	Patika	ali@patika.com	00	456789ZXC	1999-10-16	2025-09-27	2025-09-27	INDIVIDUAL_CUSTOMER
12	Aya	Patika	aya@patika.com		12345ASDF	1970-01-24	2025-09-27	2025-09-27	COOPARATE_CUSTOMER
\.


--
-- TOC entry 4976 (class 0 OID 33071)
-- Dependencies: 226
-- Data for Name: vehicle; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.vehicle (id, vehicletype, vehicleavailable, dailyprice, hourlyprice, weeklyprice, monthlyprice, createddate, updateddate, vehiclebrand, created_by, updated_by) FROM stdin;
\.


--
-- TOC entry 4994 (class 0 OID 0)
-- Dependencies: 217
-- Name: car_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.car_id_seq', 6, true);


--
-- TOC entry 4995 (class 0 OID 0)
-- Dependencies: 219
-- Name: customer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.customer_id_seq', 12, true);


--
-- TOC entry 4996 (class 0 OID 0)
-- Dependencies: 227
-- Name: helicopter_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.helicopter_id_seq', 2, true);


--
-- TOC entry 4997 (class 0 OID 0)
-- Dependencies: 229
-- Name: motorcycle_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.motorcycle_id_seq', 1, true);


--
-- TOC entry 4998 (class 0 OID 0)
-- Dependencies: 223
-- Name: payment_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.payment_id_seq', 1, false);


--
-- TOC entry 4999 (class 0 OID 0)
-- Dependencies: 221
-- Name: reservation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.reservation_id_seq', 13, true);


--
-- TOC entry 5000 (class 0 OID 0)
-- Dependencies: 225
-- Name: vehicle_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.vehicle_id_seq', 1, false);


--
-- TOC entry 5001 (class 0 OID 0)
-- Dependencies: 231
-- Name: vehicle_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.vehicle_seq', 7, true);


--
-- TOC entry 4799 (class 2606 OID 33015)
-- Name: car car_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car
    ADD CONSTRAINT car_pkey PRIMARY KEY (id);


--
-- TOC entry 4801 (class 2606 OID 33024)
-- Name: users customer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT customer_pkey PRIMARY KEY (id);


--
-- TOC entry 4809 (class 2606 OID 33087)
-- Name: helicopter helicopter_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.helicopter
    ADD CONSTRAINT helicopter_pkey PRIMARY KEY (id);


--
-- TOC entry 4811 (class 2606 OID 33097)
-- Name: motorcycle motorcycle_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.motorcycle
    ADD CONSTRAINT motorcycle_pkey PRIMARY KEY (id);


--
-- TOC entry 4805 (class 2606 OID 33052)
-- Name: payment payment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payment
    ADD CONSTRAINT payment_pkey PRIMARY KEY (id);


--
-- TOC entry 4803 (class 2606 OID 33033)
-- Name: reservation reservation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (id);


--
-- TOC entry 4807 (class 2606 OID 33078)
-- Name: vehicle vehicle_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vehicle
    ADD CONSTRAINT vehicle_pkey PRIMARY KEY (id);


--
-- TOC entry 4812 (class 2606 OID 33136)
-- Name: car car_created_by_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car
    ADD CONSTRAINT car_created_by_fkey FOREIGN KEY (created_by) REFERENCES public.users(id);


--
-- TOC entry 4813 (class 2606 OID 33146)
-- Name: car car_updated_by_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car
    ADD CONSTRAINT car_updated_by_fkey FOREIGN KEY (updated_by) REFERENCES public.users(id);


--
-- TOC entry 4818 (class 2606 OID 33161)
-- Name: helicopter helicopter_created_by_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.helicopter
    ADD CONSTRAINT helicopter_created_by_fkey FOREIGN KEY (created_by) REFERENCES public.users(id);


--
-- TOC entry 4819 (class 2606 OID 33151)
-- Name: helicopter helicopter_updated_by_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.helicopter
    ADD CONSTRAINT helicopter_updated_by_fkey FOREIGN KEY (updated_by) REFERENCES public.users(id);


--
-- TOC entry 4820 (class 2606 OID 33166)
-- Name: motorcycle motorcycle_created_by_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.motorcycle
    ADD CONSTRAINT motorcycle_created_by_fkey FOREIGN KEY (created_by) REFERENCES public.users(id);


--
-- TOC entry 4821 (class 2606 OID 33156)
-- Name: motorcycle motorcycle_updated_by_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.motorcycle
    ADD CONSTRAINT motorcycle_updated_by_fkey FOREIGN KEY (updated_by) REFERENCES public.users(id);


--
-- TOC entry 4815 (class 2606 OID 33053)
-- Name: payment payment_reservation_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payment
    ADD CONSTRAINT payment_reservation_id_fkey FOREIGN KEY (reservation_id) REFERENCES public.reservation(id);


--
-- TOC entry 4814 (class 2606 OID 33034)
-- Name: reservation reservation_customer_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_customer_id_fkey FOREIGN KEY (customer_id) REFERENCES public.users(id);


--
-- TOC entry 4816 (class 2606 OID 33131)
-- Name: vehicle vehicle_created_by_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vehicle
    ADD CONSTRAINT vehicle_created_by_fkey FOREIGN KEY (created_by) REFERENCES public.users(id);


--
-- TOC entry 4817 (class 2606 OID 33141)
-- Name: vehicle vehicle_updated_by_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vehicle
    ADD CONSTRAINT vehicle_updated_by_fkey FOREIGN KEY (updated_by) REFERENCES public.users(id);


-- Completed on 2025-09-28 11:45:35

--
-- PostgreSQL database dump complete
--

