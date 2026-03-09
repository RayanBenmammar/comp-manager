-- BIGINT cannot be cast to UUID, so we drop and recreate all tables with UUID ids.
-- This migration is safe for development environments (no production data).

DROP TABLE IF EXISTS participant;
DROP TABLE IF EXISTS division;
DROP TABLE IF EXISTS competition;

DROP SEQUENCE IF EXISTS competition_seq;
DROP SEQUENCE IF EXISTS division_seq;
DROP SEQUENCE IF EXISTS participant_seq;

CREATE TABLE competition (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE,
    timezone VARCHAR(100) NOT NULL DEFAULT 'UTC',
    location VARCHAR(255),
    description TEXT,
    status VARCHAR(50) NOT NULL DEFAULT 'DRAFT'
);

CREATE TABLE division (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    competition_id UUID NOT NULL,
    CONSTRAINT fk_division_competition FOREIGN KEY (competition_id) REFERENCES competition(id) ON DELETE CASCADE
);

CREATE TABLE participant (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    gender VARCHAR(50),
    competition_id UUID NOT NULL,
    division_id UUID NOT NULL,
    registration_date DATE NOT NULL DEFAULT CURRENT_DATE,
    status VARCHAR(50) NOT NULL DEFAULT 'REGISTERED',
    CONSTRAINT fk_participant_competition FOREIGN KEY (competition_id) REFERENCES competition(id) ON DELETE CASCADE,
    CONSTRAINT fk_participant_division FOREIGN KEY (division_id) REFERENCES division(id) ON DELETE CASCADE
);
