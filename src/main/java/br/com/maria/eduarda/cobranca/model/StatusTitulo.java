package br.com.maria.eduarda.cobranca.model;

public enum StatusTitulo {
		PENDENTE("Pendente"),
		RECEBIDO("Recebido");
		
		private String descricao;
		
		StatusTitulo(String descricao) {
			this.descricao = descricao;
		}
		
		public String getDescricao() {
			return descricao;
		}
}

