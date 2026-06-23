CREATE TABLE auth.credentials (
                                  id BIGSERIAL PRIMARY KEY,
                                  user_id UUID NOT NULL UNIQUE,
                                  email VARCHAR(255) NOT NULL UNIQUE,
                                  password_hash VARCHAR(255) NOT NULL,
                                  status VARCHAR(20) NOT NULL,
                                  created_at TIMESTAMPTZ NOT NULL,
                                  updated_at TIMESTAMPTZ NOT NULL
);

CREATE INDEX idx_credentials_user_id
    ON auth.credentials(user_id);

CREATE INDEX idx_credentials_email
    ON auth.credentials(email);