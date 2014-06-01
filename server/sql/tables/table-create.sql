-- umllint
-- 2014-04-28
-- umlcheck@dlouho.net

-- CATEGORY
CREATE SEQUENCE public.category_id_sequence INCREMENT BY 1 START WITH 1;
ALTER SEQUENCE public.category_id_sequence OWNER TO umlcheck;

CREATE TABLE public.category (
  category_id integer NOT NULL DEFAULT nextval('category_id_sequence'),
  name varchar(128) NOT NULL,
  title varchar(256),

  CONSTRAINT category_pk PRIMARY KEY (category_id)
);

ALTER TABLE public.category OWNER TO umlcheck;

-- SEVERITY
CREATE SEQUENCE public.severity_id_sequence INCREMENT BY 1 START WITH 1;
ALTER SEQUENCE public.severity_id_sequence OWNER TO umlcheck;

CREATE TABLE public.severity (
  severity_id integer NOT NULL DEFAULT nextval('severity_id_sequence'),
  name varchar(128) NOT NULL,
  title varchar(256),

  CONSTRAINT severity_pk PRIMARY KEY (severity_id)
);

ALTER TABLE public.severity OWNER TO umlcheck;

-- TAG
CREATE SEQUENCE public.tag_id_sequence INCREMENT BY 1 START WITH 1;
ALTER SEQUENCE public.tag_id_sequence OWNER TO umlcheck;

CREATE TABLE public.tag (
  tag_id integer NOT NULL DEFAULT nextval('tag_id_sequence'),
  name varchar(128) NOT NULL,
  title varchar(256),

  CONSTRAINT tag_pk PRIMARY KEY (tag_id)
);

ALTER TABLE public.tag OWNER TO umlcheck;

-- REFERENCE
CREATE SEQUENCE public.reference_id_sequence INCREMENT BY 1 START WITH 1;
ALTER SEQUENCE public.reference_id_sequence OWNER TO umlcheck;

CREATE TABLE public.reference (
  reference_id integer NOT NULL DEFAULT nextval('reference_id_sequence'),
  name varchar(128) NOT NULL,
  title varchar(256),
  description text,
  url varchar(256),
  citation varchar(512),

  CONSTRAINT reference_pk PRIMARY KEY (reference_id)
);

ALTER TABLE public.reference OWNER TO umlcheck;


-- USER
CREATE SEQUENCE public.user_id_sequence INCREMENT BY 1 START WITH 1;
ALTER SEQUENCE public.user_id_sequence OWNER TO umlcheck;

CREATE TABLE public.user (

  user_id integer NOT NULL DEFAULT nextval('user_id_sequence'),

  email varchar(256) NOT NULL,
  password varchar(256),
  hash varchar(256),

  CONSTRAINT user_pk PRIMARY KEY (user_id)
);

ALTER TABLE public.user OWNER TO umlcheck;


-- COMMENT
CREATE SEQUENCE public.comment_id_sequence INCREMENT BY 1 START WITH 1;
ALTER SEQUENCE public.comment_id_sequence OWNER TO umlcheck;

CREATE TABLE public.comment (

  comment_id integer NOT NULL DEFAULT nextval('comment_id_sequence'),

  message varchar(256) NOT NULL,

  CONSTRAINT comment_pk PRIMARY KEY (comment_id)
);

ALTER TABLE public.comment OWNER TO umlcheck;


-- MODEL
CREATE SEQUENCE public.model_id_sequence INCREMENT BY 1 START WITH 1;
ALTER SEQUENCE public.model_id_sequence OWNER TO umlcheck;

CREATE TABLE public.model (

  model_id integer NOT NULL DEFAULT nextval('model_id_sequence'),

  name varchar(128) NOT NULL,
  title varchar(256),
  code text,

  CONSTRAINT model_pk PRIMARY KEY (model_id)
);

ALTER TABLE public.model OWNER TO umlcheck;


-- PATTERN
CREATE SEQUENCE public.pattern_id_sequence INCREMENT BY 1 START WITH 1;
ALTER SEQUENCE public.pattern_id_sequence OWNER TO umlcheck;

CREATE TABLE public.pattern (
  pattern_id integer NOT NULL DEFAULT nextval('pattern_id_sequence'),
  category_id integer,
  severity_id integer,
  reference_id integer,

  name varchar(128) NOT NULL,
  title varchar(256),
  enabled bool DEFAULT true,
  code text,
  description text,

  CONSTRAINT pattern_pk PRIMARY KEY (pattern_id),

  CONSTRAINT category_fk FOREIGN KEY (category_id)
  REFERENCES public.category (category_id) MATCH FULL
  ON DELETE RESTRICT
  ON UPDATE CASCADE NOT DEFERRABLE,

  CONSTRAINT severity_fk FOREIGN KEY (severity_id)
  REFERENCES public.severity (severity_id) MATCH FULL
  ON DELETE RESTRICT
  ON UPDATE CASCADE NOT DEFERRABLE,

  CONSTRAINT reference_fk FOREIGN KEY (reference_id)
  REFERENCES public.reference (reference_id) MATCH FULL
  ON DELETE RESTRICT
  ON UPDATE CASCADE NOT DEFERRABLE
);


-- -- --


-- PATTERN USER
CREATE TABLE public.pattern_user (

  pattern_id integer,
  user_id integer,

  score int,

  CONSTRAINT pattern_fk FOREIGN KEY (pattern_id)
  REFERENCES public.pattern (pattern_id) MATCH FULL
  ON DELETE RESTRICT
  ON UPDATE CASCADE NOT DEFERRABLE,

  CONSTRAINT user_fk FOREIGN KEY (user_id)
  REFERENCES public.user (user_id) MATCH FULL
  ON DELETE RESTRICT
  ON UPDATE CASCADE NOT DEFERRABLE

);

ALTER TABLE public.pattern_user OWNER TO umlcheck;

-- PATTERN COMMENT
CREATE TABLE public.pattern_comment (

  pattern_id integer,
  comment_id integer,

  CONSTRAINT pattern_fk FOREIGN KEY (pattern_id)
  REFERENCES public.pattern (pattern_id) MATCH FULL
  ON DELETE RESTRICT
  ON UPDATE CASCADE NOT DEFERRABLE,

  CONSTRAINT comment_fk FOREIGN KEY (comment_id)
  REFERENCES public.comment (comment_id) MATCH FULL
  ON DELETE RESTRICT
  ON UPDATE CASCADE NOT DEFERRABLE
);


ALTER TABLE public.pattern_comment OWNER TO umlcheck;
