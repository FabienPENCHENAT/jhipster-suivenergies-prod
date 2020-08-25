import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SuivEnergiesTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { TD012GenerateurChauffageDeleteDialogComponent } from 'app/entities/td-012-generateur-chauffage/td-012-generateur-chauffage-delete-dialog.component';
import { TD012GenerateurChauffageService } from 'app/entities/td-012-generateur-chauffage/td-012-generateur-chauffage.service';

describe('Component Tests', () => {
  describe('TD012GenerateurChauffage Management Delete Component', () => {
    let comp: TD012GenerateurChauffageDeleteDialogComponent;
    let fixture: ComponentFixture<TD012GenerateurChauffageDeleteDialogComponent>;
    let service: TD012GenerateurChauffageService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [TD012GenerateurChauffageDeleteDialogComponent],
      })
        .overrideTemplate(TD012GenerateurChauffageDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TD012GenerateurChauffageDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TD012GenerateurChauffageService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
