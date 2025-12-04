INSERT INTO players (
    player_id,
    name,
    birth_date,
    nationality,
    uniform_number,
    height_cm,
    weight_kg,
    body_type,
    main_position,
    sub_positions,
    throw_hand,
    bat_hand,
    arm_slot,
    condition,
    fatigue,
    fitness,
    injury_status,
    injury_days_left,
    satisfaction,
    loyalty,
    potential,
    overall,
    stamina,
    composure,
    hit_contact,
    hit_power,
    plate_discipline,
    baserunning,
    fielding,
    arm_strength,
    pitch_velocity,
    pitch_control,
    pitch_stuff,
    breaking_ball,
    pickoff,
    created_at,
    updated_at
) VALUES (
           1,
           '홍길동',
           '1998-05-10',
           'KOR',
           10,
           185,
           85,
           'MUSCULAR',       -- BodyType enum
           'P',              -- Position enum
           NULL,          -- sub positions: 예) 릴리버/마무리
           'R',              -- ThrowHand
           'R',              -- BatHand
           'OVERHAND',       -- ArmSlot
           80,  -- condition
           10,  -- fatigue
           90,  -- fitness
           'HEALTHY',        -- InjuryStatus
           NULL,             -- injury_days_left
           75,               -- satisfaction
           80,               -- loyalty
           88,               -- potential
           82,               -- overall
           85,               -- stamina
           78,               -- composure
           70,               -- hit_contact
           65,               -- hit_power
           60,               -- plate_discipline
           55,               -- baserunning
           68,               -- fielding
           72,               -- arm_strength
           150,              -- pitch_velocity (km/h 가정)
           80,               -- pitch_control
           85,               -- pitch_stuff
           75,               -- breaking_ball
           70,               -- pickoff
           NOW(),            -- created_at
           NOW()             -- updated_at
       );
