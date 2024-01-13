package com.example.db.repository;

import java.sql.Timestamp;

public interface MessageProjection {
    String getName();  // Match the name returned by your function
    String getContent();
    Timestamp getTime1();
}

