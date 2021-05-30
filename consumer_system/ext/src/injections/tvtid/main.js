import tingle from 'tingle.js';
import XRegExp from "xregexp";

import 'tingle.js/dist/tingle.min.css';
import '../../includes/CrMSLogHeader';
import './custom.css';

let modal = null;

const r = () => {
    let programmeLinkNodes = document.querySelectorAll(".tv2epg-program-details-link:not(.crms)");

    programmeLinkNodes.forEach(async p => {
        let parentNode = p.parentNode;
        let defaultLink = p.getAttribute("href");
        let {
            channelId,
            programmeId
        } = XRegExp.exec(defaultLink, XRegExp(`https:\\/\\/tvtid\\.tv2\\.dk\\/program\\/(?<channelId>.*)\\/(?<programmeId>.*)`)).groups;

        if (parentNode.querySelectorAll(".crms").length > 0) {
            return;
        }

        const req = await fetch(`http://localhost/exists/${programmeId}`);
        const res = await req.json();

        if (!res) {
            return;
        }

        const creditLink = document.createElement("a");
        creditLink.href = "#";
        creditLink.onclick = function (event) {
            event.preventDefault();
            openCreditsModal({channelId, programmeId});
            return false;
        };
        creditLink.innerText = "Krediteringer";
        creditLink.classList.add("tv2epg-program-details-link");
        creditLink.classList.add("crms");
        creditLink.style.marginLeft = "1rem";

        parentNode.appendChild(creditLink);
    })

}

var openCreditsModal = async ({channelId, programmeId}) => {
    if (modal != null) {
        modal.destroy();
    }

    modal = new tingle.modal({
        closeMethods: ['overlay', 'button', 'escape'],
        footer: true,
    })

    const req = await fetch(`http://localhost/details/${programmeId}`);
    const res = await req.json();

    modal.setContent(`
        <h2>Krediteringer</h2>
        <p class="programmeTitle">${res.title}</p>
        <ul class="crms-grid">
            ${res.credits.map(cr => {
        return `
                    <li>
                        <div class="card">
                            <img src="${cr.image}" alt="${cr.name} - ${cr.role}"/>
                            <div>
                                <p class="name">${cr.name}</p>
                                <p class="role">${cr.role}</p>
                            </div>
                        </div>
                    </li>
                `;
    })}
        </ul>
    `);

    modal.addFooterBtn('Luk', 'tingle-btn tingle-btn--primary', function () {
        modal.close();
    });

    modal.open();
}

window.addEventListener("click", _ => {
    setTimeout(r, 10);
    setTimeout(r, 520);
});
