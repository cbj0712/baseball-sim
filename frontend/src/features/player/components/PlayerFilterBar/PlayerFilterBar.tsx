import type { PositionGroup, StatTier, AgeBand } from '../../lib/PlayerFilter';
import { toggleFilterValue } from '../../lib/PlayerFilter';
import styles from './PlayerFilterBar.module.scss';

interface PlayerFilterBarProps {
    keyword: string;
    onKeywordChange: (value: string) => void;

    positionGroups: PositionGroup[];
    onPositionGroupsChange: (next: PositionGroup[]) => void;

    statTiers: StatTier[];
    onStatTiersChange: (next: StatTier[]) => void;

    ageBands: AgeBand[];
    onAgeBandsChange: (next: AgeBand[]) => void;
}

export function PlayerFilterBar({
    keyword,
    onKeywordChange,
    positionGroups,
    onPositionGroupsChange,
    statTiers,
    onStatTiersChange,
    ageBands,
    onAgeBandsChange,
}: PlayerFilterBarProps) {
    return (
        <div className={styles.filters}>
            {/* 검색 */}
            <input
                type="text"
                className={styles.searchInput}
                placeholder="선수 이름 검색"
                value={keyword}
                onChange={(e) => onKeywordChange(e.target.value)}
            />

            {/* 포지션 필터 */}
            <div className={styles.filterGroup}>
                <span className={styles.filterLabel}>포지션</span>
                <div className={styles.filterButtons}>
                    <button
                        type="button"
                        className={`${styles.filterButton} ${positionGroups.length === 0 ? styles.filterButtonActive : ''
                            }`}
                        onClick={() => onPositionGroupsChange([])}
                    >
                        전체
                    </button>
                    <button
                        type="button"
                        className={`${styles.filterButton} ${positionGroups.includes('PITCHER') ? styles.filterButtonActive : ''
                            }`}
                        onClick={() =>
                            onPositionGroupsChange(toggleFilterValue(positionGroups, 'PITCHER'))
                        }
                    >
                        투수
                    </button>
                    <button
                        type="button"
                        className={`${styles.filterButton} ${positionGroups.includes('INFIELD') ? styles.filterButtonActive : ''
                            }`}
                        onClick={() =>
                            onPositionGroupsChange(toggleFilterValue(positionGroups, 'INFIELD'))
                        }
                    >
                        내야수
                    </button>
                    <button
                        type="button"
                        className={`${styles.filterButton} ${positionGroups.includes('OUTFIELD') ? styles.filterButtonActive : ''
                            }`}
                        onClick={() =>
                            onPositionGroupsChange(toggleFilterValue(positionGroups, 'OUTFIELD'))
                        }
                    >
                        외야수
                    </button>
                </div>
            </div>

            {/* 능력치 필터 */}
            <div className={styles.filterGroup}>
                <span className={styles.filterLabel}>능력치</span>
                <div className={styles.filterButtons}>
                    <button
                        type="button"
                        className={`${styles.filterButton} ${statTiers.length === 0 ? styles.filterButtonActive : ''
                            }`}
                        onClick={() => onStatTiersChange([])}
                    >
                        전체
                    </button>
                    <button
                        type="button"
                        className={`${styles.filterButton} ${statTiers.includes('TIER_90_UP') ? styles.filterButtonActive : ''
                            }`}
                        onClick={() =>
                            onStatTiersChange(toggleFilterValue(statTiers, 'TIER_90_UP'))
                        }
                    >
                        90 ↑
                    </button>
                    <button
                        type="button"
                        className={`${styles.filterButton} ${statTiers.includes('TIER_80_89') ? styles.filterButtonActive : ''
                            }`}
                        onClick={() =>
                            onStatTiersChange(toggleFilterValue(statTiers, 'TIER_80_89'))
                        }
                    >
                        80 ~ 89
                    </button>
                    <button
                        type="button"
                        className={`${styles.filterButton} ${statTiers.includes('TIER_70_79') ? styles.filterButtonActive : ''
                            }`}
                        onClick={() =>
                            onStatTiersChange(toggleFilterValue(statTiers, 'TIER_70_79'))
                        }
                    >
                        70 ~ 79
                    </button>
                    <button
                        type="button"
                        className={`${styles.filterButton} ${statTiers.includes('TIER_UNDER_70')
                                ? styles.filterButtonActive
                                : ''
                            }`}
                        onClick={() =>
                            onStatTiersChange(toggleFilterValue(statTiers, 'TIER_UNDER_70'))
                        }
                    >
                        69 ↓
                    </button>
                </div>
            </div>

            {/* 연령대 필터 */}
            <div className={styles.filterGroup}>
                <span className={styles.filterLabel}>연령대</span>
                <div className={styles.filterButtons}>
                    <button
                        type="button"
                        className={`${styles.filterButton} ${ageBands.length === 0 ? styles.filterButtonActive : ''
                            }`}
                        onClick={() => onAgeBandsChange([])}
                    >
                        전체
                    </button>
                    <button
                        type="button"
                        className={`${styles.filterButton} ${ageBands.includes('AGE_20_EARLY') ? styles.filterButtonActive : ''
                            }`}
                        onClick={() =>
                            onAgeBandsChange(toggleFilterValue(ageBands, 'AGE_20_EARLY'))
                        }
                    >
                        20대 초반
                    </button>
                    <button
                        type="button"
                        className={`${styles.filterButton} ${ageBands.includes('AGE_20_LATE') ? styles.filterButtonActive : ''
                            }`}
                        onClick={() =>
                            onAgeBandsChange(toggleFilterValue(ageBands, 'AGE_20_LATE'))
                        }
                    >
                        20대 후반
                    </button>
                    <button
                        type="button"
                        className={`${styles.filterButton} ${ageBands.includes('AGE_30_EARLY') ? styles.filterButtonActive : ''
                            }`}
                        onClick={() =>
                            onAgeBandsChange(toggleFilterValue(ageBands, 'AGE_30_EARLY'))
                        }
                    >
                        30대 초반
                    </button>
                    <button
                        type="button"
                        className={`${styles.filterButton} ${ageBands.includes('AGE_30_LATE') ? styles.filterButtonActive : ''
                            }`}
                        onClick={() =>
                            onAgeBandsChange(toggleFilterValue(ageBands, 'AGE_30_LATE'))
                        }
                    >
                        30대 후반
                    </button>
                    <button
                        type="button"
                        className={`${styles.filterButton} ${ageBands.includes('AGE_40_PLUS') ? styles.filterButtonActive : ''
                            }`}
                        onClick={() =>
                            onAgeBandsChange(toggleFilterValue(ageBands, 'AGE_40_PLUS'))
                        }
                    >
                        40대 이상
                    </button>
                </div>
            </div>
        </div>
    );
}
