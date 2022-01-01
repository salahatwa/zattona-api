package run.halo.app.utils;

import lombok.Data;

@Data
public class QrOptions {
	String render;
	boolean crisp = true;
	String ecLevel;
	int minVersion;

	String fill;
	String back;

	String text;
	int size;
	int rounded;
	int quiet;

	String mode="label";//label,image/imagelabel/labelimage

	int msize;
	int mposx;
	int mposy;
	String title;

	String label;
	String fontname;
	String fontcolor;

	String image;
	int mposx2;
	int mposy2;
}
