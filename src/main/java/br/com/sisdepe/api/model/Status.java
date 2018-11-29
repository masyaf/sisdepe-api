package br.com.sisdepe.api.model;

public enum Status {//status de aprovação para o projeto

	WAITING_FOR_APROVAL{//retorna a string se estiver com status aguardando
		@Override
		public String toString() {
			return "Waiting for approval";
		}
	}, 
	APPROVED{//retorna a string se estiver com status aprovado
		@Override
		public String toString() {
			return "Approved";
		}
	}, 
	PENDING{//retorna a string se estiver com status pendente
		@Override
		public String toString() {
			return "Pending";
		}
	},
	REFUSED{//retorna a string se estiver com status recusado
		@Override
		public String toString() {
			return "Refused";
		}
	}
}
