export interface PlayerSummaryResponse {
    id: number;
    name: string;
    mainPosition: string;
    overall: number;
    age: number;

    bodyType: string | null;
    throwHand: string | null;
    batHand: string | null;
    armSlot: string | null;

    throwHandDescription: string | null;
    batHandDescription: string | null;
    mainPositionDescription: string | null;
    armSlotDescription: string | null;
}

export interface PlayerDetailResponse {
    id: number;
    name: string;
    birthDate: string;

    nationality: string | null;
    uniformNumber: number | null;

    heightCm: number | null;
    weightKg: number | null;
    bodyType: string | null;

    mainPosition: string | null;
    subPositions: string[];
    throwHand: string | null;
    batHand: string | null;
    armSlot: string | null;

    condition: number;
    fatigue: number;
    fitness: number;
    injuryStatus: string | null;
    injuryDaysLeft: number | null;
    satisfaction: number;
    loyalty: number;

    potential: number;
    potentialGrade: string;
    overall: number;
    overallGrade: string;

    stamina: number;
    composure: number;

    hitContact: number;
    hitContactGrade: string;
    hitPower: number;
    hitPowerGrade: string;
    plateDiscipline: number;
    plateDisciplineGrade: string;
    baserunning: number;
    baserunningGrade: string;
    fielding: number;
    fieldingGrade: string;
    armStrength: number;
    armStrengthGrade: string;

    pitchVelocity: number;
    pitchVelocityGrade: string;
    pitchControl: number;
    pitchControlGrade: string;
    pitchStuff: number;
    pitchStuffGrade: string;
    breakingBall: number;
    breakingBallGrade: string;
    pickoff: number;
    pickoffGrade: string;

    throwHandDescription: string | null;
    batHandDescription: string | null;
    mainPositionDescription: string | null;
    subPositionDescriptions: string[];
    armSlotDescription: string | null;
    bodyTypeDescription: string | null;
}