# Decisões de Modelagem

1. **Relação Conta/Cliente:** só a `Conta` conhece o `Cliente` (titular).
   Evita dependência circular. As contas de um cliente são obtidas filtrando no service.
2. **Saldo guardado, não calculado:** o saldo é um atributo; as transações são histórico.
3. **Contagem de saques do mês:** feita a partir do extrato (`YearMonth`), sem contador para resetar.
4. **Transferência não conta como saque:** tem tipos próprios (ENVIADA/RECEBIDA).
5. **Tarifa é uma transação separada (`TARIFA`)**, para o extrato ser transparente.
6. **Atomicidade da transferência:** todas as validações ocorrem antes de qualquer alteração de saldo.
7. **Template Method:** `Conta` define o fluxo comum; as filhas variam via `getSaldoDisponivel()`
   e, no caso da corrente, pela tarifa.
8. **BigDecimal** para dinheiro; `Transacao` é imutável.