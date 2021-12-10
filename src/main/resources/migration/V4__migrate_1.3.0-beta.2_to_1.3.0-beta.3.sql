-- Migrate 1.3.0-beta.2 to 1.3.0-beta.3

-- Migrate options Table
alter table options alter column option_value TYPE text;
alter table options alter column option_value SET not null;

-- Migrate theme_settings Table
alter table theme_settings alter column setting_value TYPE text;
alter table theme_settings alter column setting_value SET not null;