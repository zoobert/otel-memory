CREATE SCHEMA IF NOT EXISTS ts_content;

CREATE TABLE IF NOT EXISTS ts_content.ts_geography (
    id VARCHAR(16) NOT NULL,
    parent_id VARCHAR(16),
    name VARCHAR(256) NOT NULL,
    iso_code VARCHAR(16));

CREATE UNIQUE INDEX IF NOT EXISTS ts_geography_id ON ts_content.ts_geography (id);
CREATE UNIQUE INDEX IF NOT EXISTS ts_geography_u1 ON ts_content.ts_geography (parent_id, name);
CREATE INDEX IF NOT EXISTS ts_geography_name ON ts_content.ts_geography (name);
