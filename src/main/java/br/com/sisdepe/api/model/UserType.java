package br.com.sisdepe.api.model;

public enum UserType {

	TEACHER{ 
		@Override
		public String toString() {
			
			return "Teacher";
		}
	}, 
	COORDINATOR{
		@Override
		public String toString() {
			return "Coordinator";
		}
	},
	ADMINISTRATOR{
		@Override
		public String toString() {
			return "Administrator";
		}
	}
	
}
