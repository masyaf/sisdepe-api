package br.com.sisdepe.api.model;

public enum Shift {

	NIGHT_SHIFT{
		@Override
		public String toString() {
			
			return "Night Shift";
		}
	},
	DAY_SHIFT{
		@Override
		public String toString() {
			return "DAY_SHIFT";
		}
	}, 
	MORNING_SHIFT{
		@Override
		public String toString() {
			return "Morning Shift";
		}
	}
}
