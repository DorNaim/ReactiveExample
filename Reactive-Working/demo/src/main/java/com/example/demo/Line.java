package com.example.demo;

public class Line {
	private String mContent;
	private int mLineNumber;

	public Line() {

	}

	public Line(String content, int lineNumber) {
		this.mContent = content;
		this.mLineNumber = lineNumber;
	}

	public String getmContent() {
		return mContent;
	}

	public void setmContent(String mContent) {
		this.mContent = mContent;
	}

	public int getmLineNumber() {
		return mLineNumber;
	}

	public void setmLineNumber(int mLineNumber) {
		this.mLineNumber = mLineNumber;
	}

	@Override
	public String toString() {
		return "Line [LineNumber = " + mLineNumber + ",Content = " + mContent + "]";
	}
}
