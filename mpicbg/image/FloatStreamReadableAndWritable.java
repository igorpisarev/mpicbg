package mpicbg.image;

abstract public class FloatStreamReadableAndWritable extends FloatReadableAndWritable
{
	protected int i = 0;
	final protected float[] a;
	final protected float[] data;
	final protected int numChannels;
	
	FloatStreamReadableAndWritable( FloatStream stream )
	{
		super( stream );
		data = stream.data;
		a = new float[ container.getPixelType().getNumChannels() ];
		numChannels = stream.getPixelType().getNumChannels();
	}
	
	final public void read( final float[] c )
	{
		System.arraycopy( ( ( FloatStream )container ).data, i, c, 0, c.length );
	}	
	final public float getFloatChannel( final int c )
	{
		return ( ( FloatStream )container ).data[ i + c ];
	}
	final public float[] getFloats()
	{
		final float[] f = new float[ container.getPixelType().getNumChannels() ];
		read( f );
		return f;
	}
	
	public void set( final float[] c )
	{
		synchronized ( data ){ System.arraycopy( c, 0, data, i, c.length ); }
	}
	public void setChannel( final float v, final int c )
	{
		synchronized ( data ){ data[ i + c ] = v; }
	}	
	
	public void add( final Readable c )
	{
		c.read( a );
		synchronized ( data )
		{
			for ( int j = 0; j < a.length; ++j )
				data[ i + j ] += a[ j ];
		}
	}
	public void add( final float c )
	{
		synchronized ( data )
		{
			for ( int j = 0; j < numChannels; ++j )
				data[ i + j ] += c;
		}
	}
	
	public void sub( final Readable c )
	{
		c.read( a );
		synchronized ( data )
		{
			for ( int j = 0; j < a.length; ++j )
				data[ i + j ] -= a[ j ];
		}
	}
	public void sub( final float c )
	{
		synchronized ( data )
		{
			for ( int j = 0; j < numChannels; ++j )
				data[ i + j ] -= c;
		}
	}
	
	public void mul( final Readable c )
	{
		c.read( a );
		synchronized ( data )
		{
			for ( int j = 0; j < a.length; ++j )
				data[ i + j ] *= a[ j ];
		}
	}
	public void mul( final float c )
	{
		synchronized ( data )
		{
			for ( int j = 0; j < numChannels; ++j )
				data[ i + j ] *= c;
		}
	}
	
	public void div( final Readable c )
	{
		c.read( a );
		synchronized ( data )
		{
			for ( int j = 0; j < a.length; ++j )
				data[ i + j ] /= a[ j ];
		}
	}
	public void div( final float c )
	{
		synchronized ( data )
		{
			for ( int j = 0; j < numChannels; ++j )
				data[ i + j ] /= c;
		}
	}	
}