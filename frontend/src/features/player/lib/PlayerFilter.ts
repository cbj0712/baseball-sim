import type { PlayerSummaryResponse } from '../types/player';

export type PositionGroup = 'PITCHER' | 'INFIELD' | 'OUTFIELD';

export type StatTier = 'TIER_90_UP' | 'TIER_80_89' | 'TIER_70_79' | 'TIER_UNDER_70';

export type AgeBand = 'AGE_20_EARLY' | 'AGE_20_LATE' | 'AGE_30_EARLY' | 'AGE_30_LATE' | 'AGE_40_PLUS';

export function toggleFilterValue<T>(list: T[], value: T): T[] {
    return list.includes(value)
        ? list.filter((v) => v !== value)
        : [...list, value];
}

function matchPositionFilter(player: PlayerSummaryResponse, groups: PositionGroup[]):boolean {
    if(groups.length === 0)
        return true;
    
    const pos = player.mainPosition;

    const isPitcher = pos === 'P';
    const isInfield = ['C', '1B', '2B', 'SS', '3B'].includes(pos);
    const isOutfiled = ['LF', 'CF', 'RF'].includes(pos);

    return groups.some((g) => {
        if (g === 'PITCHER')
            return isPitcher;
        if (g === 'INFIELD')
            return isInfield;
        if (g === 'OUTFIELD')
            return isOutfiled;

        return false;
    });
}

function getStatTier(overall: number): StatTier {
    if (overall >= 90)
        return 'TIER_90_UP';
    if (overall >= 80)
        return 'TIER_80_89';
    if (overall >= 70)
        return 'TIER_70_79';

    return 'TIER_UNDER_70';
}

function matchStatTierFilter(player: PlayerSummaryResponse, tiers: StatTier[]): boolean {
    if (tiers.length === 0)
        return true;

    const tier = getStatTier(player.overall);

    return tiers.includes(tier);
}

function matchAgeBandFilter(player: PlayerSummaryResponse, bands: AgeBand[]): boolean {
    if (bands.length === 0)
        return true;

    const age = player.age;

    return bands.some((band) => {
        switch(band) {
            case 'AGE_20_EARLY':
                return age >= 20 && age <= 24;
            case 'AGE_20_LATE':
                return age >= 25 && age <= 29
            case 'AGE_30_EARLY':
                return age >= 30 && age <= 34;
            case 'AGE_30_LATE':
                return age >= 35 && age <= 39;
            case 'AGE_40_PLUS':
                return age >= 40;
            default:
                return false;
        }
    });
}

export interface PlayerFilterState {
    keyword: string;
    positionGroups: PositionGroup[];
    statTiers: StatTier[];
    ageBands: AgeBand[];
}

export function applyPlayerFilters(players: PlayerSummaryResponse[], filter: PlayerFilterState): PlayerSummaryResponse[] {
    const { keyword, positionGroups, statTiers, ageBands } = filter;

    let result = [...players];

    if (keyword.trim()) {
        const lower = keyword.trim().toLowerCase();
        result = result.filter((p) => 
            p.name.toLowerCase().includes(lower),
        );
    }

    result = result.filter((p) => matchPositionFilter(p, positionGroups));
    result = result.filter((p) => matchStatTierFilter(p, statTiers));
    result = result.filter((p) => matchAgeBandFilter(p, ageBands));

    return result;
}