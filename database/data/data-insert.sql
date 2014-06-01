-- umlcheck
-- 2013-10-13_002
-- umlcheck@dlouho.net

-- CATEGORY
INSERT INTO public.category (name, title)
VALUES ('default', 'Default')
;

INSERT INTO public.category (name, title)
  VALUES ('incorrectness', 'Incorrectness')
;

-- SEVERITY
INSERT INTO public.severity (name, title)
  VALUES ('error', 'Error')
;

-- REFERENCE
INSERT INTO public.reference (name, title)
  VALUES ('qvt', 'QVTr Google Site')
;

-- PATTERN
INSERT INTO public.pattern (category_id, severity_id, reference_id, name, title, code, description)
  VALUES (currval('category_id_sequence'), currval('severity_id_sequence'), currval('reference_id_sequence'), 'pattern1', 'Test Pattern 1', 'QVTR code', 'description of the problem')
;

INSERT INTO public.pattern (category_id, severity_id, reference_id, name, title, code, description)
  VALUES (currval('category_id_sequence'), currval('severity_id_sequence'), currval('reference_id_sequence'), 'pattern2', 'Test Pattern 2', 'QVTR code 2', 'description of the problem 2')
;

-- MODEL
INSERT INTO public.model (name, title, code)
VALUES ('model1', 'Model 1', '<?xml version="1.0" encoding="UTF-8" standalone="no"?>')
;

INSERT INTO public.model (name, title, code)
VALUES ('model2', 'Model 2', '<?xml version="1.0" encoding="UTF-8" standalone="no"?>')
;
