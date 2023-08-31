START Transaction;
    UPDATE kmcs_task SET status = 0 , lab_task_id = 0 WHERE status = 1;
    DELETE FROM lab_task;
COMMIT