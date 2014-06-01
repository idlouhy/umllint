-- umllint
-- 2014-04-29
-- umllint@dlouho.net

DROP TABLE IF EXISTS public.user;
DROP TABLE IF EXISTS public.pattern;
DROP TABLE IF EXISTS public.reference;
DROP TABLE IF EXISTS public.tag;
DROP TABLE IF EXISTS public.severity;
DROP TABLE IF EXISTS public.category;
DROP TABLE IF EXISTS public.model;

DROP SEQUENCE IF EXISTS public.reference_id_sequence;
DROP SEQUENCE IF EXISTS public.tag_id_sequence;
DROP SEQUENCE IF EXISTS public.severity_id_sequence;
DROP SEQUENCE IF EXISTS public.category_id_sequence;
DROP SEQUENCE IF EXISTS public.user_id_sequence;
DROP SEQUENCE IF EXISTS public.pattern_id_sequence;
DROP SEQUENCE IF EXISTS public.model_id_sequence;
