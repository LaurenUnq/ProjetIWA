# Step 1 : Create an trigger that delete every location data that are more that 10 days old in the table user_locations and locations.`

CREATE FUNCTION delete_old_rows_locations() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
  DELETE
  FROM user_locations AS ul
  USING locations AS l
  WHERE ul.location_id = l.location_id
  AND l.location_date < NOW() - INTERVAL '10 days';
  DELETE
  FROM locations AS l
  WHERE l.location_date < NOW() - INTERVAL '10 days';
  RETURN NULL;

END;
$$;

# each time a new line is insert in the locations table, 10-days old rows are deleted

CREATE TRIGGER trigger_delete_old_rows_locations
    AFTER INSERT ON locations
    EXECUTE PROCEDURE delete_old_rows_locations();

