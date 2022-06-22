CREATE TABLE public.operationhistory
(
    history_id bigserial,
    nr1 bigint,
    op character,
    nr2 bigint,
    result double precision,
    PRIMARY KEY (history_id)
);

ALTER TABLE IF EXISTS public.operationhistory
    OWNER to postgres;