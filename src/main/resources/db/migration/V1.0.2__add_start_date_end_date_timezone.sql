ALTER TABLE competition RENAME COLUMN date TO start_date;
ALTER TABLE competition ADD COLUMN end_date DATE;
ALTER TABLE competition ADD COLUMN timezone VARCHAR(100) NOT NULL DEFAULT 'UTC';
