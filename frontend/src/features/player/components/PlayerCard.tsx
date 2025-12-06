import type { PlayerSummaryResponse } from '../types/player';
import './PlayerCard.css';

interface PlayerCardProps {
    player: PlayerSummaryResponse;
    onClick?: (id: number) => void;
}

export function PlayerCard({ player, onClick }: PlayerCardProps) {
    const handleClick = () => {
        if (onClick) {
            onClick(player.id);
        }
    }

    return (
        <div className='player-card' onClick={handleClick}>
            <div>
                <p> {player.name} </p>
                <p> {player.mainPositionDescription} ({player.age}세)</p>
                <p> {player.throwHandDescription}{player.batHandDescription} / {player.armSlotDescription ?? '-'} </p>
                <p> OVR {player.overall} </p>
            </div>
        </div>
    )
}