package com.example.db.dto;

import java.sql.Timestamp;

public interface MessageProjection {
    String getName();  // Match the name returned by your function
    String getContent();
    Timestamp getTime1();
}

