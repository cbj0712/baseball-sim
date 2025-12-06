import { useEffect, useState } from 'react';
import { fetchPlayers } from '../api/playerApi';
import type { PlayerSummaryResponse } from '../types/player';
import { PlayerCard } from '../components/PlayerCard';
import './PlayerListPage.css';

export function PlayerListPage() {
    const [players, setPlayers] = useState<PlayerSummaryResponse[]>([]);
    const [filtered, setFiltered] = useState<PlayerSummaryResponse[]>([]);
    const [keyword, setKeyword] = useState('');
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);

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
        if (!keyword.trim()) {
            setFiltered(players);
            return;
        }

        const lower = keyword.toLowerCase();

        setFiltered(
            players.filter((p) => p.name.toLowerCase().includes(lower)),
        );
    }, [keyword, players]);

    const handleCardClick = (id: number) => {
        console.log('선수 상세로 이동', id);
    }

    if (loading) {
        return <div> 선수 목록을 불러오는 중... </div>
    }

    return (
        <div className='player-page'>
            <section className='player-page__list'>
                {
                    filtered.map((player) => (
                        <PlayerCard 
                            key={player.id}
                            player={player}
                            onClick={handleCardClick}
                        />
                    ))
                }
            </section>
        </div>
    )
}