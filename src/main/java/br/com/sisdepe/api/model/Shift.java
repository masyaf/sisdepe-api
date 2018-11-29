package br.com.sisdepe.api.model;

public enum Shift {//enum de turnos

	NIGHT_SHIFT{// turno da noite, retorna string do turno
		@Override
		public String toString() {
			
			return "Night Shift";
		}
	},
	DAY_SHIFT{// turno do dia, retorna string do turno
		@Override
		public String toString() {
			return "DAY_SHIFT";
		}
	}, 
	MORNING_SHIFT{// turno da manhã, retorna string do turno
		@Override
		public String toString() {
			return "Morning Shift";
		}
	}
}
