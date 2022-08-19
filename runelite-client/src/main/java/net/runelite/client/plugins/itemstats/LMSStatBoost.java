package net.runelite.client.plugins.itemstats;

import net.runelite.api.Client;
import net.runelite.client.plugins.itemstats.delta.DeltaCalculator;
import net.runelite.client.plugins.itemstats.stats.LMSBoosts;
import net.runelite.client.plugins.itemstats.stats.Stat;

public class LMSStatBoost extends SimpleStatBoost {

	public LMSStatBoost(Stat stat, boolean boost, DeltaCalculator deltaCalculator)
	{
		super(stat, boost, deltaCalculator);
	}

	@Override
	public int heals(Client client)
	{
		int max = LMSBoosts.skillMaximum(getStat(), client);
		return getDeltaCalculator().calculateDelta(max);
	}

	@Override
	public StatChange effect(Client client)
	{
		Stat stat = getStat();
		int value = stat.getValue(client);
		int max = LMSBoosts.skillMaximum(stat, client);

		boolean hitCap = false;

		int calcedDelta = heals(client);
		if (getBoost() && calcedDelta > 0)
		{
			max += calcedDelta;
		}

		int newValue = value + calcedDelta;
		if (newValue >= max)
		{
			newValue = max;
			hitCap = true;
		}
		if (newValue < 0)
		{
			newValue = 0;
		}
		int delta = newValue - value;
		StatChange out = new StatChange();
		out.setStat(stat);
		if (delta > 0)
		{
			out.setPositivity(hitCap ? Positivity.BETTER_CAPPED : Positivity.BETTER_UNCAPPED);
		}
		else if (delta == 0)
		{
			out.setPositivity(Positivity.NO_CHANGE);
		}
		else
		{
			out.setPositivity(Positivity.WORSE);
		}
		out.setAbsolute(newValue);
		out.setRelative(delta);
		out.setTheoretical(calcedDelta);
		return out;
	}
}
