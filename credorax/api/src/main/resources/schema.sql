CREATE SEQUENCE IF NOT EXISTS "transaction_id_seq"
  START WITH 1;
CREATE TABLE IF NOT EXISTS "TRANSACTION"
(
    "ID"
    BIGINT
    NOT
    NULL,
    "INVOICE_NUMBER"
    INTEGER
    UNIQUE,
    "AMOUNT"
    BIGINT
    NOT
    NULL,
    "CURRENCY"
    VARCHAR
(
    10
) NOT NULL,
    "CARD_HOLDER_ID" BIGINT NOT NULL,
    "PAYMENT_CARD_ID" BIGINT,
    PRIMARY KEY
(
    "ID"
)
    );
--
CREATE SEQUENCE IF NOT EXISTS "card_holder_id_seq"
  START WITH 1;
CREATE TABLE IF NOT EXISTS "CARD_HOLDER"
(
    "ID"
    BIGINT
    NOT
    NULL,
    "NAME"
    VARCHAR
(
    45
) NOT NULL,
    "EMAIL" TIMESTAMP NOT NULL,
    --   "last_updated_at" TIMESTAMP   NOT NULL,
--   "data"            BYTEA,
--   "content_type"    VARCHAR(255),
--   "author"          BIGINT      NOT NULL REFERENCES "users" ("id"),
    PRIMARY KEY
(
    "ID"
)
    );
--
CREATE SEQUENCE IF NOT EXISTS "payment_card_id_seq"
  START WITH 1;
CREATE TABLE IF NOT EXISTS "PAYMENT_CARD"
(
    "ID"
    BIGINT
    NOT
    NULL,
    "PAN"
    VARCHAR
(
    45
) NOT NULL,
--   "created_at"   TIMESTAMP   NOT NULL,
    "EXPIRY" BYTEA,
    --   "content_type" VARCHAR(255),
--   "author"       BIGINT      NOT NULL REFERENCES "users" ("id"),
    PRIMARY KEY
(
    "ID"
)
    );
--
-- CREATE SEQUENCE IF NOT EXISTS "generations_id_seq"
--   START WITH 1;
-- CREATE TABLE IF NOT EXISTS "generations" (
--   "id"           BIGINT       NOT NULL,
--   "name"         VARCHAR(100) NOT NULL,
--   "created_at"   TIMESTAMP    NOT NULL,
--   "data"         BYTEA,
--   "content_type" VARCHAR(255),
--   "template"     BIGINT       NOT NULL,
--   "author"       BIGINT       NOT NULL REFERENCES "users" ("id"),
--   PRIMARY KEY ("id")
-- );
--
-- CREATE SEQUENCE IF NOT EXISTS "variables_id_seq"
--   START WITH 1;
-- CREATE TABLE IF NOT EXISTS "variables" (
--   "id"              BIGINT       NOT NULL,
--   "key"             VARCHAR(255) NOT NULL,
--   "created_at"      TIMESTAMP    NOT NULL,
--   "last_updated_at" TIMESTAMP    NOT NULL,
--   "template"        BIGINT       NOT NULL REFERENCES "templates" ("id"),
--   PRIMARY KEY ("id")
-- );
--
-- CREATE TABLE IF NOT EXISTS "template_values" (
--   "generation_id" BIGINT NOT NULL REFERENCES "generations" ("id"),
--   "variable_id"   BIGINT NOT NULL REFERENCES "variables" ("id"),
--   "value"         TEXT   NOT NULL,
--   PRIMARY KEY ("generation_id", "variable_id")
-- );

--- Login: admin; pwd: Qwerty456
-- INSERT INTO users (id, username, password, enabled, role, email) VALUES (1, 'admin', '$2a$10$H.5nRQ9FOSpb5zshCYJOqOf1wPL7t/M99FjnBXjh5v9xnoSPms66W', true, 'ADMIN', 'volodymyr.dvornyk@smallworldfs.com');
