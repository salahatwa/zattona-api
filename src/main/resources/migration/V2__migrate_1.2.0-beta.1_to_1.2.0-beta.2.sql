-- Migrate 1.2.0-beta.1 to 1.2.0-beta.2

-- Migrate journals Table
alter table journals alter column content TYPE text;
update journals set source_content='content';