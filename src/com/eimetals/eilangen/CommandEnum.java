package com.eimetals.eilangen;

public enum CommandEnum {
	
	INSERT(10) {
		@Override
		public void doOperation() {			
			
		}
	}, 
	POP(0) {
		@Override
		public void doOperation() {
			// TODO Auto-generated method stub
			
		}
	}, 
	SHOW(0) {
		@Override
		public void doOperation() {
			// TODO Auto-generated method stub
			
		}
	}, 
	EXIT(0) {
		@Override
		public void doOperation() {
			// TODO Auto-generated method stub
			
		}
	}, 
	INDEX(1) {
		@Override
		public void doOperation() {
			// TODO Auto-generated method stub
			
		}
	}, 
	GET(1) {
		@Override
		public void doOperation() {
			// TODO Auto-generated method stub
			
		}
	}, 
	REMOVE(1) {
		@Override
		public void doOperation() {
			// TODO Auto-generated method stub
			
		}
	}, 
	INSERTFRONT(10) {
		@Override
		public void doOperation() {
			// TODO Auto-generated method stub
			
		}
	}, 
	POPFRONT(0) {
		@Override
		public void doOperation() {
			// TODO Auto-generated method stub
			
		}
	}, 
	SORT(0) {
		@Override
		public void doOperation() {
			// TODO Auto-generated method stub
			
		}
	}, 
	ISSORTED(0) {
		@Override
		public void doOperation() {
			// TODO Auto-generated method stub
			
		}
	}, 
	PUSH(10) {
		@Override
		public void doOperation() {
			// TODO Auto-generated method stub
			
		}
	}, 
	EOF(0) {
		@Override
		public void doOperation() {
			// TODO Auto-generated method stub
			
		}
	};
	
	private final int numberOfArgs;

	private CommandEnum(int numberOfArgs) {
		this.numberOfArgs = numberOfArgs;
	}

	public int getNumberOfArgs() {
		return numberOfArgs;
	}
	
	public abstract void doOperation();

}
