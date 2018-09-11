package net.preibisch.mvrecon.process.fusion.nonrigid;

import java.util.Arrays;

import mpicbg.spim.data.sequence.ViewId;
import net.imglib2.realtransform.AffineTransform3D;
import net.preibisch.mvrecon.fiji.spimdata.interestpoints.InterestPoint;

public class CorrespondingIP
{
	final double[] l, corrL;
	final double[] w, corrW;
	final InterestPoint ip, corrIp;
	final ViewId viewId, corrViewId;

	public double[] avgPosW;

	public CorrespondingIP( final InterestPoint ip, final ViewId viewId, final InterestPoint corrIp, final ViewId corrViewId )
	{
		this.l = ip.getL().clone();
		this.w = ip.getL().clone();
		this.corrL = corrIp.getL().clone();
		this.corrW = corrIp.getL().clone();
		this.ip = ip;
		this.corrIp = corrIp;
		this.viewId = viewId;
		this.corrViewId = corrViewId;

		this.avgPosW = w;
	}

	protected void setAvgPosW( final double[] avgPosW ) { this.avgPosW = avgPosW; }
	public double[] getAvgPos() { return avgPosW; }

	public void transform( final AffineTransform3D t, final AffineTransform3D corrT )
	{
		t.apply( l, w );
		corrT.apply( corrL, corrW );
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ( ( corrIp == null ) ? 0 : corrIp.hashCode() );
		result = prime * result + Arrays.hashCode( corrL );
		result = prime * result
				+ ( ( corrViewId == null ) ? 0 : corrViewId.hashCode() );
		result = prime * result + ( ( ip == null ) ? 0 : ip.hashCode() );
		result = prime * result + Arrays.hashCode( l );
		result = prime * result
				+ ( ( viewId == null ) ? 0 : viewId.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		CorrespondingIP other = (CorrespondingIP) obj;
		if ( corrIp == null )
		{
			if ( other.corrIp != null )
				return false;
		} else if ( !corrIp.equals( other.corrIp ) )
			return false;
		if ( !Arrays.equals( corrL, other.corrL ) )
			return false;
		if ( corrViewId == null )
		{
			if ( other.corrViewId != null )
				return false;
		} else if ( !corrViewId.equals( other.corrViewId ) )
			return false;
		if ( ip == null )
		{
			if ( other.ip != null )
				return false;
		} else if ( !ip.equals( other.ip ) )
			return false;
		if ( !Arrays.equals( l, other.l ) )
			return false;
		if ( viewId == null )
		{
			if ( other.viewId != null )
				return false;
		} else if ( !viewId.equals( other.viewId ) )
			return false;
		return true;
	}
}