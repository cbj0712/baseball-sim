import { useEffect, useState } from 'react';
import { fetchPlayers } from '../api/playerApi';
import type { PlayerSummaryResponse } from '../types/player';
import { PlayerCard } from '../components/PlayerCard/PlayerCard';
import styles from './PlayerListPage.module.scss';
import { LineupAndPitchingPanel } from '../components/LineupAndPicthingPanel/LineupAndPitchingPanel';
import type { PositionGroup, StatTier, AgeBand } from '../lib/PlayerFilter';
import { toggleFilterValue, applyPlayerFilters } from '../lib/PlayerFilter';
import { PlayerFilterBar } from '../components/PlayerFilterBar/PlayerFilterBar';

export function PlayerListPage() {
    const [players, setPlayers] = useState<PlayerSummaryResponse[]>([]);
    const [filtered, setFiltered] = useState<PlayerSummaryResponse[]>([]);
    const [keyword, setKeyword] = useState('');
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);
    
    const [positionGroups, setPositionGroups] = useState<PositionGroup[]>([]);
    const [statTiers, setStatTiers] = useState<StatTier[]>([]);
    const [ageBands, setAgeBands] = useState<AgeBand[]>([]);

    const [filtersOpen, setFiltersOpen] = useState(true);

    useEffect(() => {
        const load = async () => {
            setLoading(true);
            setError(null);

            try {
                const data = await fetchPlayers();

                setPlayers(data);
                setFiltered(data);
            } catch(err) {
                console.error(err);
                setError('선수 목록을 불러오는 중 오류가 발생했습니다');  
            } finally {
                setLoading(false);
            }
        };

        load();
    }, []);

    useEffect(() => {
        const next = applyPlayerFilters(players, { keyword, positionGroups, statTiers, ageBands });

        setFiltered(next);
    }, [players, keyword, positionGroups, statTiers, ageBands]);

    const handleCardClick = (id: number) => {
        console.log('선수 상세로 이동', id);
    }

    if (loading) {
        return <div> 선수 목록을 불러오는 중... </div>
    }

    return (
        <section className={styles.section}>
            <header className={styles.header}>
                <div>
                    <h1 className={styles.title}>선수 관리</h1>
                    <p className={styles.subtitle}>
                        구단 보유 선수의 능력치를 한눈에 확인하고 관리합니다
                    </p>
                </div>

                <div className={styles.headerRight}>
                    <button
                        type='button'
                        className={styles.filterToggleButton}
                        onClick={() => setFiltersOpen((prev) => !prev)}
                    >
                        { filtersOpen ? '필터 접기' : '필터 펼치기'}
                    </button>
                </div>

                <div className={styles.meta}>
                    <span className={styles.metaLabel}>Roster</span>
                    <span className={styles.metaValue}>
                        {filtered.length}명 / 전체 {players.length}명
                    </span>
                </div>
            </header>

            <div className={styles.filtersArea}>
                {
                    filtersOpen && (
                        <PlayerFilterBar 
                            keyword={keyword}
                            onKeywordChange={setKeyword}
                            positionGroups={positionGroups}
                            onPositionGroupsChange={setPositionGroups}
                            statTiers={statTiers}
                            onStatTiersChange={setStatTiers}
                            ageBands={ageBands}
                            onAgeBandsChange={setAgeBands}
                        />
                    )
                }
            </div>

            <div className={styles.content}>
                {
                    loading && <div className={styles.loading}>선수 목록을 불러오는 중...</div>
                }

                {
                    !loading && error && (
                        <div className={styles.error}>
                            {error}
                        </div>
                    )
                }

                {
                    !loading && !error && filtered.length === 0 && (
                        <div className={styles.empty}>
                            조건에 맞는 선수가 없습니다
                        </div>
                    )
                }

                {
                    !loading && !error && filtered.length > 0 && (
                        <div className={styles.list}>
                            {
                                filtered.map((player) => (
                                    <PlayerCard 
                                        key={player.id}
                                        player={player}
                                        onClick={(id) => {
                                            console.log('선수 상세로 이동', id);
                                        }}
                                    />
                                ))
                            }
                        </div>
                    )
                }
            </div>
            <LineupAndPitchingPanel />
        </section>
    )
}