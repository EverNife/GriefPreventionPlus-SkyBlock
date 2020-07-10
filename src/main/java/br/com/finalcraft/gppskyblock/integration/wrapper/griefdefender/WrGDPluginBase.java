package br.com.finalcraft.gppskyblock.integration.wrapper.griefdefender;

import br.com.finalcraft.gppskyblock.GPPSkyBlock;
import br.com.finalcraft.gppskyblock.Island;
import br.com.finalcraft.gppskyblock.config.datastore.DataStore;
import br.com.finalcraft.gppskyblock.config.datastore.DataStoreGD;
import br.com.finalcraft.gppskyblock.integration.GPPluginBase;
import br.com.finalcraft.gppskyblock.integration.IClaim;
import br.com.finalcraft.gppskyblock.listeners.EventListenerGD;
import br.com.finalcraft.gppskyblock.tasks.ResetIslandThreadGD;
import com.griefdefender.api.GriefDefender;
import com.griefdefender.claim.GDClaim;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.File;

public class WrGDPluginBase extends GPPluginBase {

    @Override
    public boolean fireClaimDeleteEvent(IClaim iClaim, Player player) {
        return false;
    }

    @Override
    public DataStore setupDataStore() throws Exception{
        return new DataStoreGD(GPPSkyBlock.getInstance());
    }

    @Override
    public void registerEventListeners() throws Exception {
        GriefDefender.getEventManager().register(new EventListenerGD(GPPSkyBlock.getInstance()));
    }

    @Override
    public void setRadius(Island island, int radius) {
        Location center = island.getCenter();
        WrGDClaim claimWrapper = (WrGDClaim) island.getClaim();
        GDClaim claim = claimWrapper.claim;
        claim.resize(center.getBlockX()-radius, center.getBlockX()+radius, 0, 255, center.getBlockZ()-radius,center.getBlockZ()+radius);
    }

    @Override
    public void assyncRestoreIsland(Island island, File schematicFile) {
        new ResetIslandThreadGD(island, schematicFile);
    }
}
