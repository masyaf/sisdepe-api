package br.com.sisdepe.api.model;

public enum UserType {//enum de tipos de usuário

	TEACHER{ //professor
		@Override
		public String toString() {
			
			return "Teacher";
		}
	}, 
	COORDINATOR{//coordenador
		@Override
		public String toString() {
			return "Coordinator";
		}
	},
	ADMINISTRATOR{//administrador
		@Override
		public String toString() {
			return "Administrator";
		}
	}
	
}
