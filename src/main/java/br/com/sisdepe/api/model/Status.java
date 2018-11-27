package br.com.sisdepe.api.model;

public enum Status {

	WAITING_FOR_APROVAL{
		@Override
		public String toString() {
			return "Waiting for approval";
		}
	}, 
	APPROVED{
		@Override
		public String toString() {
			return "Approved";
		}
	}, 
	PENDING{
		@Override
		public String toString() {
			return "Pending";
		}
	},
	REFUSED{
		@Override
		public String toString() {
			return "Refused";
		}
	}
}
