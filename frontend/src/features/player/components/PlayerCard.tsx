import type { PlayerSummaryResponse } from '../types/player';
import styles from './PlayerCard.module.scss';

interface PlayerCardProps {
    player: PlayerSummaryResponse;
    onClick?: (id: number) => void;
}

export function PlayerCard({ player, onClick }: PlayerCardProps) {
    const { id, name, age, overall, mainPosition, mainPositionDescription, throwHandDescription, batHandDescription, armSlotDescription } = player;
    const positionLabel = mainPositionDescription ?? mainPosition;

    const getOverTierClass = (overall: number): string => {
        if (overall >= 90)
            return styles.ovrValueTierS;
        if (overall >= 80)
            return styles.ovrValueTierA;
        if (overall >= 70)
            return styles.ovrValueTierB;

        return styles.ovrValueTierC;
    };

    return (
        <article
            className={styles.card}
            onClick={() => onClick?.(id)}
            role='button'
        >
            <div className={styles.left}>
                <div className={styles.nameRow}>
                    <span className={styles.name}>{name}</span>
                    <span className={styles.positionBadge}>{positionLabel}</span>
                </div>
                <div className={styles.metaRow}>
                    <span className={styles.meta}>
                        {age}세 · {throwHandDescription ?? ''}{batHandDescription ? `/${batHandDescription}` : ''}
                    </span>
                </div>
            </div>

            <div className={styles.right}>
                <span className={styles.ovrLabel}>OVR</span>
                <span className={`${styles.ovrValue} ${getOverTierClass(overall)}`}>{overall}</span>
            </div>
        </article>
    )
}