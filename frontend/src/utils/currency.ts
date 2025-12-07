export function formatWonToEokMan(amount: number): string {
    if (!Number.isFinite(amount) || amount <= 0) {
        return '0억 0만원';
    }

    const EOK = 100_000_000;
    const MAN = 10_000;

    const eok = Math.floor(amount / EOK);
    const man = Math.floor((amount % EOK) / MAN);

    const eokPart = eok.toLocaleString('ko-KR');
    const manPart = man.toLocaleString('ko-KR');

    return `${eokPart}억 ${manPart}만원`;
}