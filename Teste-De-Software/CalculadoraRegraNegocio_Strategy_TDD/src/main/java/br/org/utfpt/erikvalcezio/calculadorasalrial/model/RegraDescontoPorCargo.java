package br.org.utfpt.erikvalcezio.calculadorasalrial.model;

import java.math.BigDecimal;

public enum RegraDescontoPorCargo {
	DESENVOLVEDOR(new BigDecimal(3000), 0.20, 0.10) {
		@Override
		public BigDecimal percentualDeDesconto(BigDecimal salarioFuncionario) {
			return calcularBaseDesconto(this.baseSalarial, this.basePorcetagemMaior, this.basePorcetagemMenor, salarioFuncionario);
		}
	},
	DBA(new BigDecimal(2000), 0.25, 0.15) {
		@Override
		public BigDecimal percentualDeDesconto(BigDecimal salarioFuncionario) {
			return calcularBaseDesconto(this.baseSalarial, this.basePorcetagemMaior, this.basePorcetagemMenor, salarioFuncionario);
		}
	},
	TESTADOR(new BigDecimal(2000), 0.25, 0.15) {
		@Override
		public BigDecimal percentualDeDesconto(BigDecimal salarioFuncionario) {
			return calcularBaseDesconto(this.baseSalarial, this.basePorcetagemMaior, this.basePorcetagemMenor, salarioFuncionario);
		}
	},
	GERENTE(new BigDecimal(5000), 0.30, 0.20) {
		@Override
		public BigDecimal percentualDeDesconto(BigDecimal salarioFuncionario) {
			return calcularBaseDesconto(this.baseSalarial, this.basePorcetagemMaior, this.basePorcetagemMenor, salarioFuncionario);
		}
	};

	protected BigDecimal baseSalarial;
	protected Double basePorcetagemMaior;
	protected Double basePorcetagemMenor;

	private RegraDescontoPorCargo(BigDecimal baseSalarial, Double basePorcetagemMaior, Double basePorcetagemMenor) {
		this.baseSalarial = baseSalarial;
		this.basePorcetagemMaior = basePorcetagemMaior;
		this.basePorcetagemMenor = basePorcetagemMenor;
	}

	public abstract BigDecimal percentualDeDesconto(BigDecimal salarioFuncionario);

	private static BigDecimal calcularBaseDesconto(final BigDecimal baseSalarial,
			final Double basePorcetagemMaior, final Double basePorcetagemMenor, BigDecimal salarioFuncionario) {
		if (salarioFuncionario.compareTo(baseSalarial) >= 0) {
			return salarioFuncionario.multiply(new BigDecimal(basePorcetagemMaior));
		} else {
			return salarioFuncionario.multiply(new BigDecimal(basePorcetagemMenor));
		}
	}
}
