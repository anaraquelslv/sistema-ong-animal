CREATE UNIQUE INDEX uq_raca_nome_especie ON raca (LOWER(nome), especie_id) WHERE ativo = true;
