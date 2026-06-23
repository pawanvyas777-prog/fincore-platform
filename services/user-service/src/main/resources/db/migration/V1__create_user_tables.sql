CREATE TABLE "user".user_profiles (
                                      user_id UUID PRIMARY KEY,
                                      first_name VARCHAR(100) NOT NULL,
                                      last_name VARCHAR(100) NOT NULL,
                                      email VARCHAR(255) NOT NULL UNIQUE,
                                      phone VARCHAR(20) NOT NULL UNIQUE,
                                      address VARCHAR(500),
                                      kyc_status VARCHAR(20) NOT NULL,
                                      created_at TIMESTAMPTZ NOT NULL,
                                      updated_at TIMESTAMPTZ NOT NULL
);

CREATE INDEX idx_user_profiles_email
    ON "user".user_profiles(email);

CREATE INDEX idx_user_profiles_phone
    ON "user".user_profiles(phone);

CREATE INDEX idx_user_profiles_kyc_status
    ON "user".user_profiles(kyc_status);